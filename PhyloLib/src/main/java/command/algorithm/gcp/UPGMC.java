package command.algorithm.gcp;

public final class UPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		long cij = ci + cj;
		return (ik * ci + jk * cj) / (cij) - ij * ci * cj / (cij * cij);
	}

}
