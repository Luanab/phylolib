package command.algorithm.bt.gcp;

import data.tree.Edge;

public final class UPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(Edge edge, int ci, int cj, int u, double iu, double ju, int k, Double ik, Double jk) {
		long cij = ci + cj;
		return (ik * ci + jk * cj) / (cij) - edge.distance() * ci * cj / (cij * cij);
	}

}
