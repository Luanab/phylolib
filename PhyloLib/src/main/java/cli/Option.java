package cli;

import java.util.Arrays;

public enum Option {

	OUT('o', Format.FILE),
	DATASET('d', Format.FILE),
	MATRIX('m', Format.FILE),
	TREE('t', Format.FILE),
	LVS('l', Format.NATURAL, "3");

	private final String alias;
	private final Format format;
	private final String _default;

	Option(char alias, Format format) {
		this(alias, format, null);
	}

	Option(char alias, Format format, String _default) {
		this.alias = "-" + alias;
		this.format = format;
		this._default = _default;
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

	public String _default() {
		return _default;
	}

}
