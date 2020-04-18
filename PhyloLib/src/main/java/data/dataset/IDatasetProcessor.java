package data.dataset;

import data.IReader;
import logging.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public interface IDatasetProcessor extends IReader<Dataset> {

	String IGNORING = "Ignoring invalid profile '%s'";

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
				Log.warning(IGNORING, profile.id());
		}
		return new Dataset(profiles);
	}

	default void init(Iterator<String> iterator) { }

	Profile parse(Iterator<String> iterator);

}
