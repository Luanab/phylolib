package command.algorithm.nj;

import data.tree.ClusterSet;

public final class SaitouNei extends NeighbourJoining {

	@Override
	protected double dissimilarity(ClusterSet set, int i, int j) {
		double v = set.elements().count() - 2.0;
		double sum = set.elements().filter(k -> k != i && k != j)
				.mapToDouble(k -> set.get(i, k) + set.get(j, k))
				.sum();
		double total = set.elements().filter(k -> k != i && k != j)
				.mapToDouble(k -> set.elements()
						.skip(k).filter(l -> l != i && l != j)
						.mapToDouble(l -> set.get(k, l))
						.sum())
				.sum();
		return set.get(i, j) / 2.0 + sum / (2.0 * v) + total / v;
	}

}
