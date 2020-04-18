package command.optimization;

import cli.Options;
import data.Context;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.Set;

public final class LBR extends Optimization {

	private Matrix matrix;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		this.matrix = context.getMatrix(options);
	}

	@Override
	protected Edge select(Set<Edge> edges) {
		return edges.stream().min((i, j) -> (int) (i.distance() - j.distance())).orElseThrow();
	}

	@Override
	protected Edge join(Tree tree) {
		// use matrix
		return null; /* TODO */
	}

	@Override
	protected void reduce(Edge previous, Edge current, Set<Edge> edges, Tree tree) {
		super.reduce(previous, current, edges, tree);
		if (edges.stream().min((i, j) -> (int) (i.distance() - j.distance())).orElseThrow() == current)
			edges.add(current);
	}

}
