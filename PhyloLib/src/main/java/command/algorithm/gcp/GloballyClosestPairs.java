package command.algorithm.gcp;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import javafx.util.Pair;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public abstract class GloballyClosestPairs extends Algorithm {

	private int cluster;
	private Map<Integer, Cluster> clusters;
	private PriorityQueue<Edge> edges;

	@Override
	public Tree process(Matrix matrix) {
		Tree tree = init(matrix);
		while (clusters.size() > 1) {
			Edge edge = select();
			Pair<Cluster, Cluster> clusters = join(edge, tree);
			reduce(edge, clusters.getKey(), clusters.getValue());
		}
		return tree;
	}

	private Tree init(Matrix matrix) {
		int size = matrix.size();
		this.cluster = size;
		this.clusters = new HashMap<>(size);
		this.edges = new PriorityQueue<>(size * (size - 1) / 2, Comparator.comparingDouble(Edge::distance).thenComparing(this::tiebreak));
		for (int i = 0; i < size; i++) {
			Cluster cluster = new Cluster(1, 0);
			this.clusters.put(i, cluster);
			Map<Integer, Double> distances = cluster.distances;
			for (int j = i + 1; j < size; j++) {
				double distance = Math.min(matrix.distance(i, j), matrix.distance(j, i));
				distances.put(j, distance);
				this.edges.add(new Edge(i, j, distance));
			}
		}
		return new Tree(matrix.ids());
	}

	private int tiebreak(Edge edge) {
		return !clusters.containsKey(edge.from()) || !clusters.containsKey(edge.to())
			   ? 0 :
			   -(clusters.get(edge.from()).elements + clusters.get(edge.to()).elements);
	}

	private Edge select() {
		Edge edge = edges.remove();
		while (!clusters.containsKey(edge.from()) || !clusters.containsKey(edge.to()))
			edge = edges.remove();
		return edge;
	}

	private Pair<Cluster, Cluster> join(Edge edge, Tree tree) {
		Cluster ci = clusters.remove(edge.from());
		Cluster cj = clusters.remove(edge.to());
		double branch = edge.distance() / 2;
		tree.add(new Edge(this.cluster, edge.from(), branch - ci.offset));
		tree.add(new Edge(this.cluster, edge.to(), branch - cj.offset));
		return new Pair<>(ci, cj);
	}

	private void reduce(Edge edge, Cluster ci, Cluster cj) {
		int i = edge.from();
		int j = edge.to();
		int u = this.cluster++;
		clusters.forEach((k, cluster) -> {
			Map<Integer, Double> kd = cluster.distances;
			double ik = k < i ? kd.remove(i) : ci.distances.remove(k);
			double jk = k < j ? kd.remove(j) : cj.distances.remove(k);
			double dissimilarity = dissimilarity(edge.distance(), ik, jk, ci.elements, cj.elements);
			kd.put(u, dissimilarity);
			edges.add(new Edge(k, u, dissimilarity));
		});
		clusters.put(u, new Cluster(ci.elements + cj.elements, edge.distance() / 2));
	}

	protected abstract double dissimilarity(double ij, double ik, double jk, int ci, int cj);

	private final class Cluster {

		public final int elements;
		public final double offset;
		public final Map<Integer, Double> distances;

		public Cluster(int elements, double offset) {
			this.elements = elements;
			this.offset = offset;
			this.distances = new HashMap<>(clusters.size());
		}

	}

}
