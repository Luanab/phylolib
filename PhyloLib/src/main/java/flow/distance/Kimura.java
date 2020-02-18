package flow.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class Kimura {

	private double distance(Profile a, Profile b) {
		int transitions = 0;
		int transversions = 0;
		for (int i = 0; i < a.length(); i++) {
			if (!Objects.equals(a.getLocus(i), b.getLocus(i)))
				transitions += 1;
		}
		return -Math.log((1 - 2 * transitions - transversions) * Math.sqrt(1 - 2 * transversions)) / 2;
	}

}
