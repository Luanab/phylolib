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

	protected final double dissimilarity(Edge edge) {
		return (clusters().count() - 2) * edge.distance() - clusters().mapToDouble(k -> distance(edge.from(), k) + distance(edge.to(), k)).sum();
	}

	@Override
	protected int tiebreak(Edge edge) {
		return 0;
	}

	@Override
	protected final double branch(Edge edge) {
		return edge.distance() / 2 + clusters().mapToDouble(k -> weight(k) * (distance(edge.from(), k) - distance(edge.to(), k))).sum()
									 / (2 * (clusters().count() - (weight(edge.from()) + weight(edge.to()))));
	}

	protected abstract int weight(int i);

	@Override
	protected final double offset(Edge edge) {
		return 0;
	}

	@Override
	protected final double dissimilarity(Edge edge, int u, int k) {
		int i = edge.from();
		int j = edge.to();
		double lambda = lambda(i, j);
		return lambda * (distance(i, k) - length(i, u)) + (1 - lambda) * (distance(j, k) - length(j, u));
	}

	protected abstract double lambda(int i, int j);

	protected abstract double length(int i, int j);

}
