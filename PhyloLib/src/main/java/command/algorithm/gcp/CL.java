package command.algorithm.gcp;

import data.tree.ClusterSet;

public final class CL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(ClusterSet set, int i, int j, int u, int k) {
		return Math.max(set.get(i, k), set.get(j, k));
	}

}
