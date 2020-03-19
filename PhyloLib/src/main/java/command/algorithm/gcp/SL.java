package command.algorithm.gcp;

import data.tree.ClusterSet;

public final class SL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(ClusterSet set, int i, int j, int u, int k) {
		return Math.min(set.get(i, k), set.get(j, k));
	}

}
