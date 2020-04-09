package command.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class Kimura extends Distance {

	@Override
	protected strictfp double distance(Profile a, Profile b) {
		int transitions = 0;
		int transversions = 0;
		for (int i = 0; i < a.length(); i++) {
			Integer first = a.locus(i);
			Integer second = b.locus(i);
			if (Objects.equals(first, second) || first == null || second == null)
				continue;
			int min = Math.min(first, second);
			int max = Math.max(first, second);
			if (min == 'A' && max == 'G' || min == 'C' && max == 'T')
				transitions++;
			else
				transversions++;
		}
		return -Math.log((1.0 - 2.0 * transitions - transversions) * Math.sqrt(1.0 - 2.0 * transversions)) / 2.0;
	}

}
