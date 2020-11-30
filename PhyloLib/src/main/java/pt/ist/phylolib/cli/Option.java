package pt.ist.phylolib.cli;

import java.util.Arrays;

/**
 * Enumerates the available options with their respective alias and format.
 */
public enum Option {

	OUT('o', Format.FILE),
	DATASET('d', Format.FILE),
	MATRIX('m', Format.FILE),
	TREE('t', Format.FILE),
	LVS('l', Format.NATURAL, "3");

	private final String alias;
	private final Format format;
	private final String _default;

	/**
	 * Creates an option with the given alias and value format.
	 *
	 * @param alias  the abbreviation for this option
	 * @param format the format for the value of this option
	 */
	Option(char alias, Format format) {
		this(alias, format, null);
	}

	/**
	 * Creates an option with the given alias, value format and default value.
	 *
	 * @param alias    the abbreviation for this option
	 * @param format   the format for the value of this option
	 * @param _default the default value for this option
	 */
	Option(char alias, Format format, String _default) {
		this.alias = "-" + alias;
		this.format = format;
		this._default = _default;
	}

	/**
	 * Gets the option corresponding to the given name or alias.
	 *
	 * @param name the name or alias of the option
	 *
	 * @return the corresponding Option object
	 */
	public static Option get(String name) {
		return Arrays.stream(values())
				.filter(option -> name.equals(option.toString()) || name.equals(option.alias))
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
