package command.algorithm.bt.nj;

import command.algorithm.bt.BifurcatedTree;
import data.tree.Edge;
import data.tree.Tree;

import java.util.Iterator;

public abstract class NeighbourJoining extends BifurcatedTree {

	@Override
	protected final boolean isFinished(Tree tree) {
		if (clusters().count() > 2)
			return false;
		Iterator<Integer> iterator = clusters().iterator();
		int i = iterator.next();
		int j = iterator.next();
		tree.add(new Edge(i, j, distance(i, j)));
		return true;
	}

	@Override
	protected final strictfp double dissimilarity(Edge edge) {
		return (clusters().count() - 2.0) * edge.distance() - clusters().mapToDouble(k -> distance(edge.from(), k) + distance(edge.to(), k)).sum();
	}

	@Override
	protected final strictfp double branch(int i, int j) {
		return distance(i, j) / 2.0 + (1 / (2.0 * (clusters().count() - (weight(i) + weight(j))))) * clusters()
				.mapToDouble(k -> weight(k) * (distance(i, k) - distance(j, k)))
				.sum();
	}

	protected abstract int weight(int i);

	@Override
	protected final double offset(int i, int j) {
		return 0;
	}

	@Override
	protected final strictfp double dissimilarity(int i, int j, int u, int k) {
		double lambda = lambda(i, j);
		return lambda * (distance(i, k) - length(i, u)) + (1 - lambda) * (distance(j, k) - length(j, u));
	}

	protected abstract double lambda(int i, int j);

	protected abstract double length(int i, int j);

}
