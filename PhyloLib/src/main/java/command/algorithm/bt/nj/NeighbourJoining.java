package command.algorithm.bt.nj;

import command.algorithm.bt.BifurcatedTree;
import data.tree.Edge;

public abstract class NeighbourJoining extends BifurcatedTree {

	@Override
	protected final double compare(Edge i, Edge j) {
		return dissimilarity(i.from(), i.to()) - dissimilarity(j.from(), j.to());
	}

	protected double dissimilarity(int i, int j) {
		return (ids().count() - 2.0) * distance(i, j) - ids().filter(k -> k != i && k != j)
				.mapToDouble(k -> distance(i, k) + distance(j, k))
				.sum();
	}

	@Override
	protected final strictfp double branch(int i, int j, int u) {
		return distance(i, j) / 2.0 + (1 / (2.0 * (ids().count() - clusters(u)))) * ids().filter(k -> k != i && k != j)
				.mapToDouble(k -> weight(k) * (distance(i, k) - distance(j, k)))
				.sum();
	}

	protected long clusters(int u) {
		return elements(u);
	}

	protected double weight(int k) {
		return elements(k);
	}

	@Override
	protected final strictfp double dissimilarity(int i, int j, int u, int k) {
		double lambda = lambda(i, j);
		double iu = length(i, u);
		double ju = length(j, u);
		return lambda * (distance(i, k) - iu) + (1.0 - lambda) * (distance(j, k) - ju);
	}

	protected double lambda(int i, int j) {
		return 0.5;
	}

	protected double length(int i, int j) {
		return distance(i, j);
	}

}
