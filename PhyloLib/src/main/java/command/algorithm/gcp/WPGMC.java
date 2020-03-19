package command.algorithm.gcp;

import data.tree.ClusterSet;

public final class WPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(ClusterSet set, int i, int j, int u, int k) {
		return set.get(i, k) * set.get(j, k) / 2 - set.get(i, j) / 4;
	}

}
