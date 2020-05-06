package command.algorithm.bt.gcp;

import command.algorithm.bt.BifurcatedTree;
import data.tree.Edge;
import data.tree.Tree;

public abstract class GloballyClosestPairs extends BifurcatedTree {

	@Override
	protected final boolean isFinished(Tree tree) {
		return clusters().count() == 1;
	}

	@Override
	protected double dissimilarity(Edge edge) {
		return edge.distance();
	}

	@Override
	protected int tiebreak(Edge edge) {
		return -(elements(edge.from()) + elements(edge.to()));
	}

	@Override
	protected final double branch(int i, int j) {
		return distance(i, j) / 2;
	}

	@Override
	protected final double offset(int i, int j) {
		return branch(i, j);
	}

}
