package command.algorithm.nj;

import data.tree.ClusterSet;

public class StudierKeppler extends NeighbourJoining {

	@Override
	protected double dissimilarity(ClusterSet set, int i, int j) {
		return (set.elements().count() - 2.0) * set.get(i, j) - set.elements()
				.filter(k -> k != i && k != j)
				.mapToDouble(k -> set.get(i, k) - set.get(j, k))
				.sum();
	}

}
