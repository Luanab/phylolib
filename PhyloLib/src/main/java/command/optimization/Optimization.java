package command.optimization;

import command.ICommand;
import data.tree.Edge;
import data.tree.Tree;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class Optimization implements ICommand<Tree, Tree> {

	@Override
	public final Tree process(Tree tree) {
		Set<Edge> edges = tree.edges().collect(Collectors.toSet());
		while (!edges.isEmpty()) {
			Edge previous = select(edges);
			Edge current = join(tree);
			reduce(previous, current, edges, tree);
		}
		return tree;
	}

	protected abstract Edge select(Set<Edge> edges);

	protected abstract Edge join(Tree tree);

	protected void reduce(Edge previous, Edge current, Set<Edge> edges, Tree tree) {
		edges.remove(previous);
		tree.add(current);
	}

}
