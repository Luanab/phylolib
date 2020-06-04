package data.dataset;

import data.IReader;
import logging.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class DatasetProcessor implements IReader<Dataset> {

	protected static final String INVALID = "Ignored invalid profile '%s'";

	@Override
	public Dataset parse(Stream<String> data) {
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
		return profiles.size() > 1 ? new Dataset(profiles) : null;
	}

	protected void init(Iterator<String> iterator) { }

	protected abstract Profile parse(Iterator<String> iterator);

}
