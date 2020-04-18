package command.optimization.rearrangement;

import command.optimization.Optimization;
import data.tree.Edge;
import data.tree.Tree;

import java.util.Set;

public abstract class Rearrangement extends Optimization {

	@Override
	protected final Edge select(Set<Edge> edges) {
		return edges.stream().findFirst().orElseThrow();
	}

	@Override
	protected void reduce(Edge previous, Edge current, Set<Edge> edges, Tree tree) {
		super.reduce(previous, current, edges, tree);
		/* TODO: remove v? */
	}

}
