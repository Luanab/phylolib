package command.algorithm.bt.nj;

public final class SaitouNei extends NeighbourJoining {

	@Override
	protected strictfp double dissimilarity(int i, int j) {
		double v = ids().count() - 2.0;
		double sum = ids().filter(k -> k != i && k != j)
				.mapToDouble(k -> distance(i, k) + distance(j, k))
				.sum();
		double total = ids().filter(k -> k != i && k != j)
				.mapToDouble(k -> ids()
						.skip(k).filter(l -> l != i && l != j)
						.mapToDouble(l -> distance(k, l))
						.sum())
				.sum();
		return distance(i, j) / 2.0 + sum / (2.0 * v) + total / v;
	}

	@Override
	protected double length(int i, int j) {
		return 0;
	}

}
