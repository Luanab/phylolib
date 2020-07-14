package command.distance;

import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

/**
 * Responsible for calculating a {@link Matrix distance matrix} from a {@link Dataset phylogenetic dataset} using the Hamming distance calculation.
 */
public final class Hamming extends Distance {

	@Override
	protected boolean symmetric() {
		return true;
	}

	@Override
	protected double distance(Profile i, Profile j) {
		double differences = 0;
		for (int l = 0; l < i.size(); l++)
			if (i.locus(l) == null || j.locus(l) == null || !i.locus(l).equals(j.locus(l)))
				differences++;
		return differences;
	}

}
