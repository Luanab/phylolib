package data.dataset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class FASTA implements IDatasetFormatter {

	@Override
	public Dataset parse(Stream<String> data) {
		List<Profile> profiles = new ArrayList<>();
		Iterator<String> iterator = data.iterator();
		String next = iterator.next();
		while (iterator.hasNext()) {
			String id = next.substring(1);
			StringBuilder sequence = new StringBuilder();
			while (iterator.hasNext() && !(next = iterator.next()).startsWith(">"))
				sequence.append(next);
			profiles.add(new Profile(id, sequence.toString()));
		}
		return new Dataset(profiles.stream());
	}

}
