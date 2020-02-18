package data.dataset;

public final class Profile {

	private final String id;
	private final Integer[] loci;

	public Profile(String id, Integer[] loci) {
		this.id = id;
		this.loci = loci;
	}

	public int length() {
		return loci.length;
	}

	public String getId() {
		return id;
	}

	public Integer getLocus(int i) {
		return loci[i];
	}

}
