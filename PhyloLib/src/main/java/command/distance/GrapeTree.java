package command.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class GrapeTree extends Distance {

	@Override
	protected strictfp double distance(Profile a, Profile b) {
		double distance = 0;
		double missings = 0;
		for (int i = 0; i < a.length(); i++) {
			if (b.locus(i) == null)
				missings++;
			if (!Objects.equals(a.locus(i), b.locus(i)) && b.locus(i) != null)
				distance++;
		}
		return distance / missings;
	}

}
