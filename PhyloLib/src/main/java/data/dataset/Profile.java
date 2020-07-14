package data.dataset;

import java.util.stream.Stream;

/**
 * Represents a profile as an id and a set of loci.
 */
public final class Profile {

	private final String id;
	private final Integer[] loci;

	/**
	 * Creates a profile corresponding to the given id and loci represented by characters.
	 *
	 * @param id   the id of this profile
	 * @param loci the characters composing the loci of this profile
	 */
	public Profile(String id, String loci) {
		this.id = id;
		this.loci = loci.chars().mapToObj(value -> value == ' ' || value == '-' ? null : value).toArray(Integer[]::new);
	}

	/**
	 * Creates a profile corresponding to the given id and loci represented by ids.
	 *
	 * @param id   the id of this profile
	 * @param loci the ids composing the loci of this profile
	 */
	public Profile(String id, Stream<String> loci) {
		this.id = id;
		this.loci = loci.map(value -> value.isBlank() || value.equals("-") ? null : Integer.valueOf(value)).toArray(Integer[]::new);
	}

	public String id() {
		return id;
	}

	public int size() {
		return loci.length;
	}

	public Integer locus(int i) {
		return loci[i];
	}

}
