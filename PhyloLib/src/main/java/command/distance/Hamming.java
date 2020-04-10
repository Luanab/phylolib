package command.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class Hamming extends Distance {

	@Override
	protected double distance(Profile i, Profile j) {
		double differences = 0;
		for (int l = 0; l < i.length(); l++)
			if (!Objects.equals(i.locus(l), j.locus(l)))
				differences++;
		return differences;
	}

}
