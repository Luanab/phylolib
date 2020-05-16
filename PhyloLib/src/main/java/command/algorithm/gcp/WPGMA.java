package command.algorithm.gcp;

public final class WPGMA extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return (ik + jk) / 2;
	}

}
