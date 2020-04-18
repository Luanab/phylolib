package data.dataset;

import data.IReader;
import javafx.util.Pair;
import logging.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public interface IDatasetProcessor extends IReader<Dataset> {

	String RENAMING = "Renaming profile '%s' to '%d'";
	String IGNORING = "Ignoring invalid profile '%s'";

	@Override
	default Dataset parse(Stream<String> data) {
		Iterator<String> iterator = data.iterator();
		List<Profile> profiles = new ArrayList<>();
		init(iterator);
		while (iterator.hasNext()) {
			Pair<String, Profile> pair = parse(iterator);
			String id = pair.getKey();
			Profile profile = pair.getValue();
			if (profile != null && profile.length() > 1 && (profiles.isEmpty() || profile.length() == profiles.get(0).length())) {
				Log.info(RENAMING, id, profiles.size());
				profiles.add(profile);
			} else
				Log.warning(IGNORING, id);
		}
		return new Dataset(profiles);
	}

	default void init(Iterator<String> iterator) { }

	Pair<String, Profile> parse(Iterator<String> iterator);

}
