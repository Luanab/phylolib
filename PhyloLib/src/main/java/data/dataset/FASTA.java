package data.dataset;

import logging.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class FASTA implements IDatasetProcessor {

	@Override
	public Dataset parse(Stream<String> data) {
		Iterator<String> iterator = data.iterator();
		List<Profile> profiles = new ArrayList<>();
		String next = iterator.hasNext() ? iterator.next() : null;
		int counter = 1;
		while (iterator.hasNext()) {
			String id = next.substring(1);
			StringBuilder sequence = new StringBuilder();
			while (iterator.hasNext() && !(next = iterator.next()).startsWith(">")) {
				if (!next.matches("^[ACTG ]*$")) {
					sequence = new StringBuilder();
					break;
				}
				sequence.append(next);
			}
			if (sequence.length() > 0 && (profiles.isEmpty() || sequence.length() == profiles.get(0).length()))
				profiles.add(new Profile(id, sequence.toString()));
			else
				Log.warning(INVALID_PROFILE, counter);
			counter++;
		}
		return profiles.isEmpty() ? null : new Dataset(profiles);
	}

}
