package command.algorithm.mst;

import command.algorithm.Algorithm;
import data.tree.ClusterSet;

public abstract class MinimumSpanningTree extends Algorithm {

	@Override
	protected final double dissimilarity(ClusterSet set, int i, int j) {
		return set.get(i, j);
	}

	@Override
	protected final double branch(ClusterSet set, int i, int j, int u) {
		// ignore this step
		return 0;
	}

	@Override
	protected final void reduce(ClusterSet set, int i, int j, int u) {
		// ignore this step
	}

	@Override
	protected final double dissimilarity(ClusterSet set, int i, int j, int u, int k) {
		// ignore this step
		return 0;
	}

}
