package command.distance;

import data.dataset.Profile;

public final class GrapeTree extends Distance {

	@Override
	protected strictfp double distance(Profile i, Profile j) {
		double differences = 0;
		double nonmissing = 0;
		for (int l = 0; l < i.size(); l++) {
			if (j.locus(l) != null) {
				nonmissing++;
				if (!j.locus(l).equals(i.locus(l)))
					differences++;
			}
		}
		return differences / nonmissing;
	}

}
