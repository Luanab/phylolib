package command.algorithm.mst;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MinimumSpanningTree extends Algorithm {

	private Matrix matrix;
	private int[] clusters;
	private List<Edge> edges;

	@Override
	protected void init(Matrix matrix) {
		int size = matrix.size();
		this.matrix = matrix;
		this.clusters = new int[size];
		this.edges = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			this.clusters[i] = i;
			for (int j = 0; j < size; j++)
				if (i != j)
					this.edges.add(new Edge(i, j, matrix.distance(i, j)));
		}
	}

	protected final int elements() {
		return matrix.size();
	}

	protected final double distance(int i, int j) {
		return matrix.distance(i, j);
	}

	protected final int id(int i) {
		return Integer.parseInt(matrix.ids()[i]);
	}

	@Override
	protected final boolean isFinished(Tree tree) {
		return Arrays.stream(clusters).distinct().count() == 1;
	}

	@Override
	protected final Edge select() {
		return edges.stream()
				.min((i, j) -> i.distance() == j.distance() ? tiebreak(i.from(), i.to(), j.from(), j.to()) : (int) (i.distance() - j.distance()))
				.orElseThrow();
	}

	protected abstract int tiebreak(int ifrom, int ito, int jfrom, int jto);

	@Override
	protected final void join(Edge edge) {
		// Ignore this step
	}

	@Override
	protected final void reduce(Edge edge, Tree tree) {
		int i = clusters[edge.from()];
		int j = clusters[edge.to()];
		for (int index = 0; index < clusters.length; index++)
			if (clusters[index] == j)
				clusters[index] = i;
		edges.removeIf(e -> clusters[e.from()] == clusters[e.to()]);
		tree.add(edge);
	}

}
