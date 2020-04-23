package command.optimization;

import cli.Options;
import data.Context;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public final class LBR extends Optimization {

	private Matrix matrix;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		this.matrix = context.getMatrix(options);
	}

	@Override
	protected Optional<Edge> select(Stream<Edge> edges) {
		return edges.min((i, j) -> (int) (i.distance() - j.distance()));
	}

	@Override
	protected Edge join(Tree tree) {
		// use matrix
		return null; /* TODO */
	}

	@Override
	protected void reduce(Edge edge, Set<Edge> edges) {
		if (edges.stream().min((i, j) -> (int) (i.distance() - j.distance())).orElseThrow() == edge)
			edges.add(edge);
	}

}
