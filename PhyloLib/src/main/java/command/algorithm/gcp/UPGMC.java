package command.algorithm.gcp;

import data.tree.ClusterSet;

public final class UPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(ClusterSet set, int i, int j, int u, int k) {
		double ci = set.count(i);
		double cj = set.count(j);
		return (set.get(i, k) * ci + set.get(j, k) * cj) / (ci + cj) - set.get(i, j) * ci * cj / Math.pow(ci + cj, 2);
	}

}
