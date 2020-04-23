package cli;

import java.util.Arrays;
import java.util.Optional;

public enum Option {

	OUT('o', Format.FILE),
	DATASET('d', Format.FILE),
	MATRIX('m', Format.FILE),
	TREE('t', Format.FILE),
	LVS('l', Format.NATURAL);

	private final char alias;
	private final Format format;

	Option(char alias, Format format) {
		this.alias = alias;
		this.format = format;
	}

	public static Optional<Option> get(String key) {
		return Arrays.stream(values()).filter(option -> key.equals("--" + option.toString()) || key.equals("-" + option.alias)).findFirst();
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public Format getFormat() {
		return format;
	}
}
