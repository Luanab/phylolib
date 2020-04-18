package data.dataset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public final class ML implements IDatasetProcessor {

	@Override
	public Profile parse(Iterator<String> iterator) {
		String[] profile = iterator.next().split("\\t");
		String[] loci = Arrays.copyOfRange(profile, 1, profile.length);
		return new Profile(profile[0], Arrays.stream(loci).allMatch(l -> l.matches("^(\\d+| |-)?$")) ? Arrays.stream(loci) : Stream.empty());
	}

}
