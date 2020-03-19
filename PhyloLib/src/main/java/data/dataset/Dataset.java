package data.dataset;

import java.util.stream.Stream;

public final class Dataset {

	private final Profile[] profiles;

	public Dataset(Stream<Profile> profiles) {
		this.profiles = profiles.toArray(Profile[]::new);
	}

	public int size() {
		return profiles.length;
	}

	public Profile get(int i) {
		return profiles[i];
	}

}
