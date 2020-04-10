package command.algorithm.bt.gcp;

public final class SL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(int i, int j, int u, int k) {
		return Math.min(distance(i, k), distance(j, k));
	}

}
