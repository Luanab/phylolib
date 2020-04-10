package command.algorithm.bt.gcp;

import command.algorithm.bt.BifurcatedTree;
import data.tree.Edge;

public abstract class GloballyClosestPairs extends BifurcatedTree {

	@Override
	protected final double compare(Edge i, Edge j) {
		return i.distance() - j.distance();
	}

	@Override
	protected final double branch(int i, int j, int u) {
		return distance(i, j) / 2;
	}

}
