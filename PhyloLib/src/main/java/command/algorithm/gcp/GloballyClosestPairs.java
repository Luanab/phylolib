package command.algorithm.gcp;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.ClusterSet;
import data.tree.Pair;

public abstract class GloballyClosestPairs extends Algorithm {

	@Override
	protected final double dissimilarity(ClusterSet set, int i, int j) {
		return set.get(i, j);
	}

	@Override
	protected final int tiebreak(Matrix matrix, ClusterSet set, Pair<Integer, Integer> i, Pair<Integer, Integer> j) {
		return 0;
	}

	@Override
	protected final double branch(ClusterSet set, int i, int j, int u) {
		return set.get(i, j) / 2;
	}

}
