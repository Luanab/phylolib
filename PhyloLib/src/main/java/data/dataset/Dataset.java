package data.dataset;

public final class Dataset {

	private final Profile[] profiles;

	public Dataset(Profile[] profiles) {
		this.profiles = profiles;
	}

	public int size() {
		return profiles.length;
	}

	public Profile get(int i) {
		return profiles[i];
	}

}
