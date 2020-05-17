package command.algorithm.gcp;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

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
		Tree tree = new Tree(matrix.ids());
		init(matrix);
		while (clusters.size() > 1) {
			Edge edge = select();
			join(edge, tree);
			reduce(edge);
		}
		return tree;
	}

	private void init(Matrix matrix) {
		int size = matrix.size();
		this.cluster = size;
		this.clusters = new HashMap<>(size);
		this.edges = new PriorityQueue<>(size * (size - 1) / 2, Comparator.comparingDouble(Edge::distance).thenComparing(this::tiebreak));
		for (int i = 0; i < size; i++)
			this.clusters.put(i, new Cluster(1, 0));
		for (int i = 0; i < size; i++)
			for (int j = i + 1; j < size; j++) {
				double distance = Math.min(matrix.distance(i, j), matrix.distance(j, i));
				this.clusters.get(j).distances.put(i, distance);
				this.edges.add(new Edge(j, i, distance));
			}
	}

	protected int tiebreak(Edge edge) {
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

	private void join(Edge edge, Tree tree) {
		Cluster ci = clusters.get(edge.from());
		Cluster cj = clusters.get(edge.to());
		double branch = edge.distance() / 2;
		Cluster cluster = new Cluster(ci.elements + cj.elements, branch);
		clusters.put(this.cluster, cluster);
		tree.add(new Edge(this.cluster, edge.from(), branch - ci.offset));
		tree.add(new Edge(this.cluster, edge.to(), branch - cj.offset));
	}

	private void reduce(Edge edge) {
		int cluster = this.cluster++;
		Cluster ci = clusters.get(edge.from());
		Cluster cj = clusters.get(edge.to());
		Map<Integer, Double> ud = clusters.get(cluster).distances;
		clusters.keySet().stream()
				.filter(k -> k != cluster && k != edge.from() && k != edge.to())
				.forEach(k -> {
					double ik = clusters.get(Math.max(edge.from(), k)).distances.get(Math.min(edge.from(), k));
					double jk = clusters.get(Math.max(edge.to(), k)).distances.get(Math.min(edge.to(), k));
					double dissimilarity = dissimilarity(edge.distance(), ik, jk, ci.elements, cj.elements);
					ud.put(k, dissimilarity);
					edges.add(new Edge(cluster, k, dissimilarity));
				});
		clusters.remove(edge.from());
		clusters.remove(edge.to());
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
