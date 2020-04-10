package command.distance;

import data.dataset.Profile;

import java.util.Objects;

public final class GrapeTree extends Distance {

	@Override
	protected strictfp double distance(Profile i, Profile j) {
		double differences = 0;
		double nonmissing = 0;
		for (int l = 0; l < i.length(); l++) {
			if (j.locus(l) != null) {
				nonmissing++;
				if (!Objects.equals(i.locus(l), j.locus(l)))
					differences++;
			}
		}
		return differences / nonmissing;
	}

}
