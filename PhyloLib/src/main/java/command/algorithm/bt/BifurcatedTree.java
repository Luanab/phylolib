package command.algorithm.bt;

import cli.Options;
import command.algorithm.Algorithm;
import data.Context;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class BifurcatedTree extends Algorithm {

	private int cluster;
	private Map<Integer, Cluster> clusters;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		Matrix matrix = context.getMatrix(options);
		this.cluster = matrix.size();
		this.clusters = new HashMap<>();
		for (int i = 0; i < this.cluster; i++) {
			Cluster cluster = new Cluster(1);
			this.clusters.put(i, cluster);
			for (int j = 0; j < this.cluster; j++)
				cluster.distances.put(j, matrix.get(i, j));
		}
	}

	@Override
	protected final long distinct() {
		return clusters.size();
	}

	protected final Edge select() {
		return ids()
				.flatMap(i -> ids().filter(j -> !i.equals(j)).map(j -> new Edge(i, j, distance(i, j))))
				.min((i, j) -> (int) compare(i, j))
				.orElseThrow();
	}

	protected final Stream<Integer> ids() {
		return clusters.keySet().stream();
	}

	protected final double distance(int i, int j) {
		return clusters.get(i).distances.get(j);
	}

	protected abstract double compare(Edge i, Edge j);

	@Override
	protected final void join(Edge edge) {
		int i = edge.from();
		int j = edge.to();
		double iu = branch(i, j, this.cluster);
		double ju = distance(i, j) - iu;
		Cluster cluster = new Cluster(elements(i) + elements(j));
		clusters.put(this.cluster, cluster);
		cluster.distances.put(i, iu);
		cluster.distances.put(j, ju);
		clusters.keySet().forEach(k -> {
			if (k != this.cluster && k != i && k != j) {
				double distance = dissimilarity(i, j, this.cluster, k);
				cluster.distances.put(k, distance);
				clusters.get(k).distances.put(this.cluster, distance);
			}
		});
	}

	protected final int elements(int i) {
		return clusters.get(i).elements;
	}

	protected abstract double branch(int i, int j, int u);

	protected abstract double dissimilarity(int i, int j, int u, int k);

	@Override
	protected final void reduce(Edge edge, Tree tree) {
		int i = edge.from();
		int j = edge.to();
		int u = cluster++;
		clusters.remove(i);
		clusters.remove(j);
		tree.add(new Edge(u, i, distance(u, i)));
		tree.add(new Edge(u, j, distance(u, j)));
	}

	private static final class Cluster {

		public final int elements;
		public final Map<Integer, Double> distances;

		public Cluster(int elements) {
			this.elements = elements;
			this.distances = new HashMap<>();
		}

	}

}
