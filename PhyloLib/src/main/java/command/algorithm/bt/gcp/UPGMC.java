package command.algorithm.bt.gcp;

import data.tree.Edge;

public final class UPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(Edge edge, int u, int k) {
		long ci = elements(edge.from());
		long cj = elements(edge.to());
		long cij = ci + cj;
		return (distance(edge.from(), k) * ci + distance(edge.to(), k) * cj) / (cij) - edge.distance() * ci * cj / (cij * cij);
	}

}
