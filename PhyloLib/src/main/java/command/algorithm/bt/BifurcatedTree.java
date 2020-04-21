package command.algorithm.bt;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class BifurcatedTree extends Algorithm {

	private int cluster;
	private Map<Integer, Cluster> clusters;

	@Override
	protected final void init(Matrix matrix) {
		this.cluster = matrix.size();
		this.clusters = new HashMap<>();
		for (int i = 0; i < this.cluster; i++) {
			Cluster cluster = new Cluster(1, 0);
			this.clusters.put(i, cluster);
			for (int j = 0; j < this.cluster; j++)
				cluster.distances.put(j, matrix.distance(i, j));
		}
	}

	protected final Stream<Integer> ids() {
		return clusters.keySet().stream();
	}

	protected final double distance(int i, int j) {
		return clusters.get(i).distances.get(j);
	}

	protected final int elements(int i) {
		return clusters.get(i).elements;
	}

	protected final int clusters() {
		return clusters.size();
	}

	@Override
	protected final Edge select() {
		return ids()
				.flatMap(i -> ids().filter(j -> !i.equals(j)).map(j -> new Edge(i, j, distance(i, j))))
				.min((i, j) -> (int) (dissimilarity(i) - dissimilarity(j)))
				.orElseThrow();
	}

	protected abstract double dissimilarity(Edge edge);

	@Override
	protected final void join(Edge edge) {
		int i = edge.from();
		int j = edge.to();
		double iu = branch(i, j);
		double ju = edge.distance() - iu;
		clusters.get(i).distances.put(this.cluster, iu);
		clusters.get(j).distances.put(this.cluster, ju);
		Cluster cluster = new Cluster(elements(i) + elements(j), offset(i, j));
		clusters.put(this.cluster, cluster);
		cluster.distances.put(this.cluster, 0.0);
		clusters.keySet().forEach(k -> {
			if (k != this.cluster && k != i && k != j) {
				double distance = dissimilarity(i, j, this.cluster, k);
				cluster.distances.put(k, distance);
				Map<Integer, Double> distances = clusters.get(k).distances;
				distances.remove(i);
				distances.remove(j);
				distances.put(this.cluster, distance);
			}
		});
	}

	protected abstract double branch(int i, int j);

	protected abstract double offset(int i, int j);

	protected abstract double dissimilarity(int i, int j, int u, int k);

	@Override
	protected final void reduce(Edge edge, Tree tree) {
		int i = edge.from();
		int j = edge.to();
		int u = cluster++;
		tree.add(new Edge(u, i, distance(i, u) - clusters.get(i).offset));
		tree.add(new Edge(u, j, distance(j, u) - clusters.get(j).offset));
		clusters.remove(i);
		clusters.remove(j);
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
