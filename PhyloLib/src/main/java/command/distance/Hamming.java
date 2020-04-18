package command.distance;

import data.dataset.Profile;

public final class Hamming extends Distance {

	@Override
	protected double distance(Profile i, Profile j) {
		double differences = 0;
		for (int l = 0; l < i.length(); l++)
			if (i.locus(l) == null || j.locus(l) == null || !i.locus(l).equals(j.locus(l)))
				differences++;
		return differences;
	}

}
