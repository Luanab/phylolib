package command.algorithm.gcp;

public final class UPGMA extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return (ik * ci + jk * cj) / (ci + cj);
	}

}
