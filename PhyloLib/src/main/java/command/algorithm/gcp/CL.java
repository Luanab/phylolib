package command.algorithm.gcp;

import data.matrix.Matrix;
import data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix} using the Complete-Linkage algorithm.
 */
public final class CL extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return Math.max(ik, jk);
	}

}
