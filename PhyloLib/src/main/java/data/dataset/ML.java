package data.dataset;

import logging.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class ML implements IDatasetProcessor {

	@Override
	public Dataset parse(Stream<String> data) {
		Iterator<String[]> iterator = data.map(line -> line.split("\\t")).iterator();
		List<Profile> profiles = new ArrayList<>();
		int counter = 1;
		while (iterator.hasNext()) {
			String[] next = iterator.next();
			if (next.length > 2 && Arrays.stream(next).allMatch(locus -> locus.matches("^(\\d+| )$")) && (profiles.isEmpty() || next.length - 1 == profiles.get(0).length()))
				profiles.add(new Profile(next[0], Arrays.stream(next).skip(1)));
			else
				Log.warning(INVALID_PROFILE, counter);
			counter++;
		}
		return profiles.isEmpty() ? null : new Dataset(profiles);
	}

}
