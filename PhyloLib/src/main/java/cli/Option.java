package cli;

import java.util.Arrays;

public enum Option {

	OUT('o', Format.FILE),
	DATASET('d', Format.FILE),
	MATRIX('m', Format.FILE),
	TREE('t', Format.FILE),
	LVS('l', Format.NATURAL);

	private final String alias;
	private final Format format;

	Option(char alias, Format format) {
		this.alias = "-" + alias;
		this.format = format;
	}

	public static Option get(String key) {
		return Arrays.stream(values())
				.filter(option -> key.equalsIgnoreCase(option.toString()) || key.equalsIgnoreCase(option.alias))
				.findFirst()
				.orElse(null);
	}

	@Override
	public String toString() {
		return "--" + name().toLowerCase();
	}

	public Format format() {
		return format;
	}
}
