package command.algorithm.nj;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.ClusterSet;
import data.tree.Pair;

public abstract class NeighbourJoining extends Algorithm {

	@Override
	protected final int tiebreak(Matrix matrix, ClusterSet set, Pair<Integer, Integer> i, Pair<Integer, Integer> j) {
		return 0;
	}

	@Override
	protected final double branch(ClusterSet set, int i, int j, int u) {
		return (set.get(i, j) / 2.0) + set.elements()
											   .filter(k -> k != i && k != j)
											   .mapToDouble(k -> weight(set, k) * (set.get(i, k) - set.get(j, k)))
											   .sum() / (2.0 * (set.elements().count() - clusters(set, u)));
	}

	protected long clusters(ClusterSet set, int u) {
		return set.count(u);
	}

	protected double weight(ClusterSet set, int k) {
		return set.count(k);
	}

	@Override
	protected final double dissimilarity(ClusterSet set, int i, int j, int u, int k) {
		double lambda = lambda(set, i, j);
		return lambda * (set.get(i, k) - set.get(i, u)) + (1.0 - lambda) * (set.get(j, k) - set.get(j, u));
	}

	protected double lambda(ClusterSet clusterSet, int i, int j) {
		return 0.5;
	}

}
