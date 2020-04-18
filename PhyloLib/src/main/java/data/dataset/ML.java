package data.dataset;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Iterator;

public final class ML implements IDatasetProcessor {

	@Override
	public Pair<String, Profile> parse(Iterator<String> iterator) {
		String[] profile = iterator.next().split("\\t");
		String[] loci = Arrays.copyOfRange(profile, 1, profile.length);
		return new Pair<>(profile[0], Arrays.stream(loci).allMatch(l -> l.matches("^(\\d+| |-)?$")) ? new Profile(loci) : null);
	}

}
