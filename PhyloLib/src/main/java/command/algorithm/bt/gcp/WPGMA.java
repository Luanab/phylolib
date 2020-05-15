package command.algorithm.bt.gcp;

import data.tree.Edge;

public final class WPGMA extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(Edge edge, int u, int k) {
		return (distance(edge.from(), k) + distance(edge.to(), k)) / 2;
	}

}
