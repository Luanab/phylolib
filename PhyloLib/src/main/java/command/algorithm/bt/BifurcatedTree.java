package command.algorithm.bt;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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
			Edge edge = clusters.entrySet().stream()
					.flatMap(i -> i.getValue().distances.entrySet().stream()
							.map(j -> new Edge(i.getKey(), j.getKey(), j.getValue())))
					.min(Comparator.comparingDouble((ToDoubleFunction<Edge>) this::dissimilarity).thenComparing(this::tiebreak))
					.orElseThrow();

			int u = cluster++;
			double iu = branch(edge);
			double ju = edge.distance() - iu;
			Cluster i = clusters.remove(edge.from());
			Cluster j = clusters.remove(edge.to());
			Cluster cluster = new Cluster(i.elements + j.elements, offset(edge));
			clusters.put(u, cluster);
			tree.add(new Edge(u, edge.from(), iu - i.offset));
			tree.add(new Edge(u, edge.to(), ju - j.offset));

			clusters.keySet().forEach(k -> {
				if (k != u) {
					double distance = dissimilarity(edge, i.elements, j.elements, u, iu, ju, k, i.distances.get(k), j.distances.get(k));
					cluster.distances.put(k, distance);
					Map<Integer, Double> distances = clusters.get(k).distances;
					distances.remove(edge.from());
					distances.remove(edge.to());
					distances.put(u, distance);
				}
			});
		}
		return tree;
	}

	private void init(Matrix matrix) {
		int size = matrix.size();
		this.cluster = size;
		this.clusters = new HashMap<>(size);
		for (int i = 0; i < size; i++) {
			Cluster cluster = new Cluster(1, 0);
			this.clusters.put(i, cluster);
			for (int j = 0; j < size; j++)
				if (i != j)
					cluster.distances.put(j, matrix.distance(i, j));
		}
	}

	protected final Stream<Integer> clusters() {
		return clusters.keySet().stream();
	}

	protected final double distance(int i, int j) {
		return i == j ? 0 : clusters.get(i).distances.get(j);
	}

	protected final int elements(int i) {
		return clusters.get(i).elements;
	}

	protected abstract boolean isFinished(Tree tree);

	protected abstract double dissimilarity(Edge edge);

	protected abstract int tiebreak(Edge edge);

	protected abstract double branch(Edge edge);

	protected abstract double offset(Edge edge);

	protected abstract double dissimilarity(Edge edge, int ci, int cj, int u, double iu, double ju, int k, Double ik, Double jk);

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
