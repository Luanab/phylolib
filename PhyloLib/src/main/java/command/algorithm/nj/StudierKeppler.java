package command.algorithm.nj;

import data.matrix.Matrix;
import data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix} using the Neighbour Joining algorithm by Studier and Keppler.
 */
public final class StudierKeppler extends NeighbourJoining {

	@Override
	protected int weight(Cluster i) {
		return 1;
	}

	@Override
	protected double lambda(Cluster ci, Cluster cj) {
		return 0.5;
	}

	@Override
	protected double length(double distance) {
		return distance;
	}

}
