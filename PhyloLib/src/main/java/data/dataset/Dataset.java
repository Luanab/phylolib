package data.dataset;

import java.util.List;

public final class Dataset {

	private final Profile[] profiles;

	public Dataset(List<Profile> profiles) {
		this.profiles = profiles.toArray(Profile[]::new);
	}

	public int size() {
		return profiles.length;
	}

	public Profile profile(int i) {
		return profiles[i];
	}

}
