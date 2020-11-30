package pt.ist.phylolib.command.distance;

import pt.ist.phylolib.data.dataset.Dataset;
import pt.ist.phylolib.data.dataset.Profile;
import pt.ist.phylolib.data.matrix.Matrix;

/**
 * Responsible for calculating a {@link Matrix distance matrix} from a {@link Dataset phylogenetic dataset} using the Kimura distance calculation.
 */
public final class Kimura extends Distance {

	@Override
	protected boolean symmetric() {
		return true;
	}

	@Override
	protected double distance(Profile i, Profile j) {
		double transitions = 0;
		double transversions = 0;
		for (int l = 0; l < i.size(); l++) {
			Integer first = i.locus(l);
			Integer second = j.locus(l);
			if (first == null || second == null || first.equals(second))
				continue;
			int min = Math.min(first, second);
			int max = Math.max(first, second);
			if ((min == 'A' && max == 'G') || (min == 'C' && max == 'T'))
				transitions++;
			else
				transversions++;
		}
		transitions /= i.size();
		transversions /= i.size();
		return -Math.log((1 - 2 * transitions - transversions) * Math.sqrt(1 - 2 * transversions)) / 2;
	}

}
