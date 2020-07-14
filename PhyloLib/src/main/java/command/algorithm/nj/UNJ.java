package command.algorithm.nj;

import data.matrix.Matrix;
import data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix} using the Unweighted Neighbour Joining algorithm.
 */
public final class UNJ extends NeighbourJoining {

	@Override
	protected int weight(Cluster i) {
		return i.elements;
	}

	@Override
	protected double lambda(Cluster ci, Cluster cj) {
		return ci.elements / (double) (ci.elements + cj.elements);
	}

	@Override
	protected double length(double distance) {
		return distance;
	}

}
