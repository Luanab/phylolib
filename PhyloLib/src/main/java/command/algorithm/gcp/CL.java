package command.algorithm.gcp;

public final class CL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return Math.max(ik, jk);
	}

}
