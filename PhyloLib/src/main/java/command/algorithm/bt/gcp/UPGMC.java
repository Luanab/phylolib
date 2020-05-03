package command.algorithm.bt.gcp;

public final class UPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(int i, int j, int u, int k) {
		long ci = elements(i);
		long cj = elements(j);
		long cij = ci + cj;
		return (distance(i, k) * ci + distance(j, k) * cj) / (cij) - distance(i, j) * ci * cj / (cij * cij);
	}

}
