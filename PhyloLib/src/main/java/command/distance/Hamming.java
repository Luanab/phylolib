package command.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class Hamming extends Distance {

	@Override
	protected double distance(Profile a, Profile b) {
		double distance = 0;
		for (int i = 0; i < a.length(); i++)
			if (!Objects.equals(a.locus(i), b.locus(i)))
				distance++;
		return distance;
	}

}
