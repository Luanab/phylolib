package pt.ist.phylolib.cli;

import java.lang.reflect.Constructor;

/**
 * Represents the parameters of a command as a type and a set of options.
 */
public final class Parameters {

	private final Constructor<?> type;
	private final Options options;

	/**
	 * Creates the parameters of a command corresponding to a given type of command and respective options.
	 *
	 * @param type    the constructor for type of the command
	 * @param options the options of the command
	 */
	public Parameters(Constructor<?> type, Options options) {
		this.type = type;
		this.options = options;
	}

	public Constructor<?> type() {
		return type;
	}

	public Options options() {
		return options;
	}

}
