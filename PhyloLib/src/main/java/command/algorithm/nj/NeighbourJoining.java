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
		Tree tree = new Tree(matrix.ids());
		init(matrix);
		while (clusters.size() > 2) {
			Edge edge = clusters.entrySet().stream()
					.flatMap(i -> i.getValue().distances.entrySet().stream()
							.map(j -> new Edge(i.getKey(), j.getKey(), j.getValue())))
					.map(e -> new Pair<>(e, dissimilarity(e)))
					.min(Comparator.comparingDouble(Pair::getValue))
					.map(Pair::getKey)
					.orElseThrow();

			int u = cluster++;
			double iu = branch(edge);
			double ju = edge.distance() - iu;
			Cluster i = clusters.remove(edge.from());
			Cluster j = clusters.remove(edge.to());
			Cluster cluster = new Cluster(i.elements + j.elements);
			clusters.put(u, cluster);
			tree.add(new Edge(u, edge.from(), iu));
			tree.add(new Edge(u, edge.to(), ju));

			clusters.keySet().forEach(k -> {
				if (k != u) {
					double distance = dissimilarity(edge, iu, ju, i.distances.get(k), j.distances.get(k));
					cluster.distances.put(k, distance);
					Map<Integer, Double> distances = clusters.get(k).distances;
					distances.remove(edge.from());
					distances.remove(edge.to());
					distances.put(u, distance);
				}
			});
		}
		Iterator<Integer> iterator = clusters.keySet().iterator();
		int i = iterator.next();
		int j = iterator.next();
		tree.add(new Edge(i, j, distance(i, j)));
		return tree;
	}

	private void init(Matrix matrix) {
		int size = matrix.size();
		this.cluster = size;
		this.clusters = new HashMap<>(size);
		for (int i = 0; i < size; i++) {
			Cluster cluster = new Cluster(1);
			this.clusters.put(i, cluster);
			for (int j = 0; j < size; j++)
				if (i != j)
					cluster.distances.put(j, matrix.distance(i, j));
		}
	}

	protected final double distance(int i, int j) {
		return i == j ? 0 : clusters.get(i).distances.get(j);
	}

	protected final int elements(int i) {
		return clusters.get(i).elements;
	}

	protected final double dissimilarity(Edge edge) {
		return (clusters.size() - 2) * edge.distance() - clusters.keySet().stream().mapToDouble(k -> distance(edge.from(), k) + distance(edge.to(), k)).sum();
	}

	protected final double branch(Edge edge) {
		return edge.distance() / 2 + clusters.keySet().stream().mapToDouble(k -> weight(k) * (distance(edge.from(), k) - distance(edge.to(), k))).sum()
									 / (2 * (clusters.size() - (weight(edge.from()) + weight(edge.to()))));
	}

	protected abstract int weight(int i);

	protected final double dissimilarity(Edge edge, double iu, double ju, Double ik, Double jk) {
		double lambda = lambda(edge.from(), edge.to());
		return lambda * (ik - length(iu)) + (1 - lambda) * (jk - length(ju));
	}

	protected abstract double lambda(int i, int j);

	protected abstract double length(double distance);

	private final class Cluster {

		public final int elements;
		public final Map<Integer, Double> distances;

		public Cluster(int elements) {
			this.elements = elements;
			this.distances = new HashMap<>(clusters.size());
		}

	}

}
