package data.dataset;

import java.util.stream.Stream;

public final class Profile {

	private final String id;
	private final Integer[] loci;

	public Profile(String id, String loci) {
		this.id = id;
		this.loci = loci.chars().mapToObj(value -> value == ' ' ? null : value).toArray(Integer[]::new);
	}

	public Profile(String id, Stream<String> loci) {
		this.id = id;
		this.loci = loci.map(value -> value.isBlank() ? null : Integer.valueOf(value)).toArray(Integer[]::new);
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
