package command.algorithm.mst;

import cli.Options;
import command.algorithm.Algorithm;
import data.Context;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public abstract class MinimumSpanningTree extends Algorithm {

	private Matrix matrix;
	private int[] clusters;
	private List<Edge> edges;

	protected final int elements() {
		return matrix.size();
	}

	protected final double distance(int i, int j) {
		return matrix.get(i, j);
	}

	protected String[] ids() {
		return matrix.ids();
	}

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		super.init(context, options);
		this.matrix = context.getMatrix(options);
		int size = matrix.size();
		this.clusters = new int[size];
		this.edges = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			this.clusters[i] = i;
			for (int j = 0; j < size; j++)
				if (i != j)
					this.edges.add(new Edge(i, j, matrix.get(i, j)));
		}
	}

	@Override
	protected final long distinct() {
		return Arrays.stream(clusters).distinct().count();
	}

	@Override
	protected final Edge select() {
		return edges.stream()
				.min((i, j) -> i.distance() == j.distance()
							   ? tiebreak(i.from(), i.to(), j.from(), j.to())
							   : (int) (i.distance() - j.distance()))
				.orElseThrow();
	}

	protected abstract int tiebreak(int ifrom, int ito, int jfrom, int jto);

	@Override
	protected final void join(Edge edge) {
		// ignore this step
	}

	@Override
	protected final void reduce(Edge edge, Tree tree) {
		int i = clusters[edge.from()];
		int j = clusters[edge.to()];
		for (int index = 0; index < clusters.length; index++)
			if (clusters[index] == j)
				clusters[index] = i;
		remove(e -> clusters[e.from()] == clusters[e.to()]);
		tree.add(edge);
	}

	protected final void remove(Predicate<Edge> predicate) {
		edges.removeIf(predicate);
	}

}
