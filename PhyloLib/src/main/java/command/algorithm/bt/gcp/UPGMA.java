package command.algorithm.bt.gcp;

import data.tree.Edge;

public final class UPGMA extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(Edge edge, int u, int k) {
		long ci = elements(edge.from());
		long cj = elements(edge.to());
		return (distance(edge.from(), k) * ci + distance(edge.to(), k) * cj) / (ci + cj);
	}

}
