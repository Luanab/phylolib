package command.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class Kimura extends Distance {

	@Override
	protected strictfp double distance(Profile i, Profile j) {
		double transitions = 0;
		double transversions = 0;
		for (int l = 0; l < i.length(); l++) {
			Integer first = i.locus(l);
			Integer second = j.locus(l);
			if (Objects.equals(first, second) || first == null || second == null)
				continue;
			int min = Math.min(first, second);
			int max = Math.max(first, second);
			if ((min == 'A' && max == 'G') || (min == 'C' && max == 'T'))
				transitions++;
			else
				transversions++;
		}
		transitions /= i.length();
		transversions /= i.length();
		return -Math.log((1.0 - 2.0 * transitions - transversions) * Math.sqrt(1.0 - 2.0 * transversions)) / 2.0;
	}

}
