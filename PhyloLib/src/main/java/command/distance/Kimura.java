package command.distance;

import data.dataset.Profile;

public final class Kimura extends Distance {

	@Override
	protected strictfp double distance(Profile i, Profile j) {
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
		return -Math.log((1.0 - 2.0 * transitions - transversions) * Math.sqrt(1.0 - 2.0 * transversions)) / 2.0;
	}

}
