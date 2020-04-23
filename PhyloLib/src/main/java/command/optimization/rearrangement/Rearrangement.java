package command.optimization.rearrangement;

import command.optimization.Optimization;
import data.tree.Edge;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Rearrangement extends Optimization {

	@Override
	protected final Optional<Edge> select(Stream<Edge> edges) {
		return edges.findFirst();
	}

	@Override
	protected void reduce(Edge edge, Set<Edge> edges) {
		/* TODO: remove v? */
	}

}
