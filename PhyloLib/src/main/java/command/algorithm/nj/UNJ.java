package command.algorithm.nj;

import data.tree.ClusterSet;

public final class UNJ extends StudierKeppler {

	@Override
	protected long clusters(ClusterSet set, int u) {
		return 2;
	}

	@Override
	protected double weight(ClusterSet set, int k) {
		return 1;
	}

	@Override
	protected double lambda(ClusterSet clusterSet, int i, int j) {
		double count = clusterSet.count(i);
		return count / (count + clusterSet.count(j));
	}

}
