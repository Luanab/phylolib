package command.algorithm.bt.gcp;

import data.tree.Edge;

public final class CL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(Edge edge, int u, int k) {
		return Math.max(distance(edge.from(), k), distance(edge.to(), k));
	}

}
