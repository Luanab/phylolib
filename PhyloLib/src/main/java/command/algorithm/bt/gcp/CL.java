package command.algorithm.bt.gcp;

public final class CL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(int i, int j, int u, int k) {
		return Math.max(distance(i, k), distance(j, k));
	}

}
