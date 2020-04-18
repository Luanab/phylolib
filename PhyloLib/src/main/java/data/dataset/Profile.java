package data.dataset;

import java.util.stream.Stream;

public final class Profile {

	private final String id;
	private final Integer[] loci;

	public Profile(String id, String loci) {
		this.id = id;
		this.loci = loci.chars().mapToObj(value -> value == ' ' || value == '-' ? null : value).toArray(Integer[]::new);
	}

	public Profile(String id, Stream<String> loci) {
		this.id = id;
		this.loci = loci.map(value -> value.isBlank() || value.equals("-") ? null : Integer.valueOf(value)).toArray(Integer[]::new);
	}

	public String id() {
		return id;
	}

	public int size() {
		return loci != null ? loci.length : 0;
	}

	public Integer locus(int i) {
		return loci[i];
	}

}
