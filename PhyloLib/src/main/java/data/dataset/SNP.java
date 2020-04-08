package data.dataset;

import javafx.util.Pair;

import java.util.Iterator;

public final class SNP implements IDatasetProcessor {

	@Override
	public Pair<String, Profile> parse(Iterator<String> iterator) {
		String[] profile = iterator.next().split("\\t", 2);
		return new Pair<>(profile[0], profile.length == 2 && profile[1].matches("^[0 1]+$") ? new Profile(profile[1]) : null);
	}

}
