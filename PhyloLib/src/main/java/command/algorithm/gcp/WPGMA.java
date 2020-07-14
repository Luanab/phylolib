package command.algorithm.gcp;

import data.matrix.Matrix;
import data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix} using the Weighted Pair Group Method with Arithmetic-mean algorithm.
 */
public final class WPGMA extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return (ik + jk) / 2;
	}

}
