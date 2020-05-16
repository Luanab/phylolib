package command.algorithm.gcp;

public final class SL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return Math.min(ik, jk);
	}

}
