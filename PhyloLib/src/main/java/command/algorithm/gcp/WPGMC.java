package command.algorithm.gcp;

public final class WPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return (ik + jk) / 2 - ij / 4;
	}

}
