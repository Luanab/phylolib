package command.algorithm.bt;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

public abstract class BifurcatedTree extends Algorithm {

	private int cluster;
	private Map<Integer, Cluster> clusters;

	@Override
	public Tree process(Matrix matrix) {
		Tree tree = new Tree(matrix.ids());
		init(matrix);
		while (!isFinished(tree)) {
			Edge edge = select();
			join(edge);
			reduce(edge, tree);
		}
		return tree;
	}

	private void init(Matrix matrix) {
		this.cluster = matrix.size();
		this.clusters = new HashMap<>();
		for (int i = 0; i < this.cluster; i++) {
			Cluster cluster = new Cluster(1, 0);
			this.clusters.put(i, cluster);
			for (int j = 0; j < this.cluster; j++)
				cluster.distances.put(j, matrix.distance(i, j));
		}
	}

	protected final Stream<Integer> clusters() {
		return clusters.keySet().stream();
	}

	protected final double distance(int i, int j) {
		return clusters.get(i).distances.get(j);
	}

	protected final int elements(int i) {
		return clusters.get(i).elements;
	}

	protected abstract boolean isFinished(Tree tree);

	private Edge select() {
		return clusters()
				.flatMap(i -> clusters().filter(j -> !i.equals(j)).map(j -> new Edge(i, j, distance(i, j))))
				.min(Comparator.comparingDouble((ToDoubleFunction<Edge>) this::dissimilarity).thenComparing(this::tiebreak))
				.orElseThrow();
	}

	protected abstract double dissimilarity(Edge edge);

	protected abstract int tiebreak(Edge edge);

	private void join(Edge edge) {
		int i = edge.from();
		int j = edge.to();
		double branch = branch(edge);
		clusters.get(i).distances.put(this.cluster, branch);
		clusters.get(j).distances.put(this.cluster, edge.distance() - branch);
		Cluster cluster = new Cluster(elements(i) + elements(j), offset(edge));
		clusters.put(this.cluster, cluster);
		cluster.distances.put(this.cluster, 0.0);
		clusters.keySet().forEach(k -> {
			if (k != this.cluster && k != i && k != j) {
				double distance = dissimilarity(edge, this.cluster, k);
				cluster.distances.put(k, distance);
				Map<Integer, Double> distances = clusters.get(k).distances;
				distances.remove(i);
				distances.remove(j);
				distances.put(this.cluster, distance);
			}
		});
	}

	protected abstract double branch(Edge edge);

	protected abstract double offset(Edge edge);

	protected abstract double dissimilarity(Edge edge, int u, int k);

	private void reduce(Edge edge, Tree tree) {
		int i = edge.from();
		int j = edge.to();
		int u = cluster++;
		tree.add(new Edge(u, i, distance(i, u) - clusters.remove(i).offset));
		tree.add(new Edge(u, j, distance(j, u) - clusters.remove(j).offset));
	}

	private static final class Cluster {

		public final int elements;
		public final double offset;
		public final Map<Integer, Double> distances;

		public Cluster(int elements, double offset) {
			this.elements = elements;
			this.offset = offset;
			this.distances = new HashMap<>();
		}

	}

}
