package data.dataset;

import java.util.Arrays;

public final class Profile {

	private final Integer[] loci;

	public Profile(String loci) {
		this.loci = loci.chars().mapToObj(value -> value == ' ' ? null : value).toArray(Integer[]::new);
	}

	public Profile(String[] loci) {
		this.loci = Arrays.stream(loci).map(value -> value.isBlank() ? null : Integer.valueOf(value)).toArray(Integer[]::new);
	}

	public int length() {
		return loci.length;
	}

	public Integer locus(int i) {
		return loci[i];
	}

}
