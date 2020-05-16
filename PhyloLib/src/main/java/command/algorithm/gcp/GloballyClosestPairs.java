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
		this.edges = new PriorityQueue<>(size * (size - 1), Comparator.comparingDouble(Edge::distance).thenComparing(this::tiebreak));
		for (int i = 0; i < size; i++)
			this.clusters.put(i, new Cluster(1, 0));
		for (int i = 0; i < size; i++)
			for (int j = i + 1; j < size; j++)
				this.edges.add(new Edge(j, i, Math.min(matrix.distance(i, j), matrix.distance(j, i))));
	}

	protected int tiebreak(Edge edge) {
		return -(clusters.get(edge.from()).elements + clusters.get(edge.to()).elements);
	}

	private Edge select() {
		return edges.remove();
	}

	private void join(Edge edge, Tree tree) {
		Cluster i = clusters.get(edge.from());
		Cluster j = clusters.get(edge.to());
		double branch = edge.distance() / 2;
		Cluster cluster = new Cluster(i.elements + j.elements, branch);
		clusters.put(this.cluster, cluster);
		tree.add(new Edge(this.cluster, edge.from(), branch - i.offset));
		tree.add(new Edge(this.cluster, edge.to(), branch - j.offset));
	}

	private void reduce(Edge edge) {
		Cluster i = clusters.get(edge.from());
		Cluster j = clusters.get(edge.to());
		int u = cluster++;
		clusters.keySet().forEach(k -> {
			if (k != u && k != edge.from() && k != edge.to()) {
				Edge ik = edge(edge.from(), k);
				Edge jk = edge(edge.to(), k);
				edges.remove(ik);
				edges.remove(jk);
				edges.add(new Edge(u, k, dissimilarity(edge.distance(), ik.distance(), jk.distance(), i.elements, j.elements)));
			}
		});
		clusters.remove(edge.from());
		clusters.remove(edge.to());
	}

	private Edge edge(int i, int j) {
		return edges.stream()
				.filter(e -> (e.from() == i && e.to() == j) || (e.from() == j && e.to() == i))
				.findFirst()
				.orElseThrow();
	}

	protected abstract double dissimilarity(double ij, double ik, double jk, int ci, int cj);

	private static final class Cluster {

		public final int elements;
		public final double offset;

		public Cluster(int elements, double offset) {
			this.elements = elements;
			this.offset = offset;
		}

	}

}
