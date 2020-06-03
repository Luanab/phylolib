package command.algorithm.nj;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class NeighbourJoining extends Algorithm {

	private int cluster;
	private Map<Integer, Cluster> clusters;

	@Override
	public Tree process(Matrix matrix) {
		Tree tree = init(matrix);
		while (clusters.size() > 2) {
			Edge edge = select();
			Pair<Pair<Cluster, Cluster>, Pair<Double, Double>> values = join(edge, tree);
			Pair<Cluster, Cluster> clusters = values.getKey();
			Pair<Double, Double> distances = values.getValue();
			reduce(edge, clusters.getKey(), clusters.getValue(), distances.getKey(), distances.getValue());
		}
		finish(tree);
		return tree;
	}

	private Tree init(Matrix matrix) {
		int size = matrix.size();
		this.cluster = size;
		this.clusters = new HashMap<>(size);
		for (int i = 0; i < size; i++)
			this.clusters.put(i, new Cluster(1));
		for (int i = 0; i < size; i++) {
			Cluster cluster = this.clusters.get(i);
			for (int j = i + 1; j < size; j++) {
				double distance = Math.min(matrix.distance(i, j), matrix.distance(j, i));
				cluster.distances.put(j, distance);
				cluster.sum += distance;
				this.clusters.get(j).sum += distance;
			}
		}
		return new Tree(matrix.ids());
	}

	private Edge select() {
		return clusters.entrySet().stream()
				.flatMap(i -> clusters.entrySet().stream()
						.filter(j -> j.getKey() > i.getKey())
						.map(j -> dissimilarity(i.getKey(), j.getKey(), i.getValue(), j.getValue())))
				.min(Comparator.comparingDouble(Pair::getValue))
				.map(Pair::getKey)
				.orElseThrow();
	}

	private Pair<Edge, Double> dissimilarity(int i, int j, Cluster ci, Cluster cj) {
		double ij = ci.distances.get(j);
		return new Pair<>(new Edge(i, j, ij), (clusters.size() - 2) * ij - ci.sum - cj.sum);
	}

	private Pair<Pair<Cluster, Cluster>, Pair<Double, Double>> join(Edge edge, Tree tree) {
		Cluster ci = clusters.remove(edge.from());
		Cluster cj = clusters.remove(edge.to());
		double iu = branch(edge, ci, cj);
		double ju = edge.distance() - iu;
		tree.add(new Edge(this.cluster, edge.from(), iu));
		tree.add(new Edge(this.cluster, edge.to(), ju));
		return new Pair<>(new Pair<>(ci, cj), new Pair<>(iu, ju));
	}

	private double branch(Edge edge, Cluster ci, Cluster cj) {
		return edge.distance() / 2 + (ci.sum - cj.sum) / (2 * (clusters.size() + 2 - (weight(ci) + weight(cj))));
	}

	protected abstract int weight(Cluster i);

	private void reduce(Edge edge, Cluster ci, Cluster cj, double iu, double ju) {
		int i = edge.from();
		int j = edge.to();
		int u = this.cluster++;
		Cluster cu = new Cluster(ci.elements + cj.elements);
		clusters.forEach((k, ck) -> {
			Map<Integer, Double> dk = ck.distances;
			Double ik = k < i ? dk.remove(i) : ci.distances.remove(k);
			Double jk = k < j ? dk.remove(j) : cj.distances.remove(k);
			double dissimilarity = dissimilarity(ci, cj, iu, ju, ik, jk);
			dk.put(u, dissimilarity);
			ck.sum += weight(cu) * dissimilarity - ik - jk;
			cu.sum += weight(ck) * dissimilarity;
		});
		clusters.put(u, cu);
	}

	private double dissimilarity(Cluster ci, Cluster cj, double iu, double ju, Double ik, Double jk) {
		double lambda = lambda(ci, cj);
		return lambda * (ik - length(iu)) + (1 - lambda) * (jk - length(ju));
	}

	protected abstract double lambda(Cluster ci, Cluster cj);

	protected abstract double length(double distance);

	private void finish(Tree tree) {
		Iterator<Map.Entry<Integer, Cluster>> iterator = clusters.entrySet().iterator();
		Map.Entry<Integer, Cluster> first = iterator.next();
		int i = first.getKey();
		Cluster ci = first.getValue();
		Map.Entry<Integer, Cluster> second = iterator.next();
		int j = second.getKey();
		Cluster cj = second.getValue();
		tree.add(new Edge(i, j, i < j ? ci.distances.get(j) : cj.distances.get(i)));
	}

	protected final class Cluster {

		public final int elements;
		public final Map<Integer, Double> distances;

		public double sum;

		public Cluster(int elements) {
			this.elements = elements;
			this.distances = new HashMap<>(clusters.size());
			this.sum = 0;
		}

	}

}
