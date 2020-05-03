package data.dataset;

import data.IReader;
import logging.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public interface IDatasetProcessor extends IReader<Dataset> {

	String INVALID = "Ignored invalid profile '%s'";

	@Override
	default Dataset parse(Stream<String> data) {
		Iterator<String> iterator = data.iterator();
		List<Profile> profiles = new ArrayList<>();
		init(iterator);
		while (iterator.hasNext()) {
			Profile profile = parse(iterator);
			if (profile.size() > 1 && (profiles.isEmpty() || profile.size() == profiles.get(0).size()))
				profiles.add(profile);
			else
				Log.warning(INVALID, profile.id());
		}
		return profiles.isEmpty() ? null : new Dataset(profiles);
	}

	default void init(Iterator<String> iterator) { }

	Profile parse(Iterator<String> iterator);

}
