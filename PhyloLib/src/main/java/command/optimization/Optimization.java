package command.optimization;

import command.ICommand;
import data.tree.Edge;
import data.tree.Tree;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Optimization implements ICommand<Tree, Tree> {

	@Override
	public final Tree process(Tree tree) {
		Set<Edge> edges = tree.edges().collect(Collectors.toSet());
		while (!edges.isEmpty()) {
			Edge previous = select(edges, tree);
			Edge current = join(tree);
			reduce(previous, current, edges, tree);
		}
		return tree;
	}

	protected final Edge select(Set<Edge> edges, Tree tree) {
		Edge edge = select(edges.stream()).orElseThrow();
		tree.remove(edge);
		return edge;
	}

	protected abstract Optional<Edge> select(Stream<Edge> edges);

	protected abstract Edge join(Tree tree);

	protected void reduce(Edge previous, Edge current, Set<Edge> edges, Tree tree) {
		edges.remove(previous);
		tree.add(current);
		reduce(current, edges);
	}

	protected abstract void reduce(Edge edge, Set<Edge> edges);

}
