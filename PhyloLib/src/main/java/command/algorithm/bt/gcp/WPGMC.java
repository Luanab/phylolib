package command.algorithm.bt.gcp;

import data.tree.Edge;

public final class WPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(Edge edge, int ci, int cj, int u, double iu, double ju, int k, Double ik, Double jk) {
		return (ik + jk) / 2 - edge.distance() / 4;
	}

}
