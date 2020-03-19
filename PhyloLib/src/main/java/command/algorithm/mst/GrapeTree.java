package command.algorithm.mst;

import data.matrix.Matrix;
import data.tree.ClusterSet;
import data.tree.Pair;

public final class GrapeTree extends MinimumSpanningTree {

	@Override
	protected int tiebreak(Matrix matrix, ClusterSet set, Pair<Integer, Integer> i, Pair<Integer, Integer> j) {
		return (int) (Math.min(mean(set, i.getFirst()), mean(set, i.getSecond())) -
					  Math.min(mean(set, j.getFirst()), mean(set, j.getSecond())));
	}

	private double mean(ClusterSet set, int i) {
		double sum = set.elements()
				.filter(j -> i != j)
				.mapToDouble(j -> Math.pow(set.get(i, j), -1))
				.sum();
		return Math.pow(sum / (set.elements().count() - 1), -1);
	}

}
