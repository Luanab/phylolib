package pt.ist.phylolib.command.algorithm.gcp;

import pt.ist.phylolib.data.matrix.Matrix;
import pt.ist.phylolib.data.tree.Tree;

/**
 * Responsible for calculating a {@link Tree phylogenetic tree} from a {@link Matrix distance matrix} using the Weighted Pair Group Method with Centroid algorithm.
 */
public final class WPGMC extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(double ij, double ik, double jk, int ci, int cj) {
		return (ik + jk) / 2 - ij / 4;
	}

}
