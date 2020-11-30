package pt.ist.phylolib.cli;

import pt.ist.phylolib.logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the options of a command as keys and values.
 */
public final class Options {

	private static final String DEFAULT = "Used default value '%s' for option %s";
	private static final String INVALID_OPTION = "Ignored invalid option '%s'";
	private static final String DUPLICATED_OPTION = "Ignored duplicated option %s";

	private final Map<Option, String> options = new HashMap<>();

	/**
	 * Saves the association between the key-value option given as parameter.
	 * <p>
	 * Throws a warning if the given option is invalid or duplicated.
	 *
	 * @param option the key-value option
	 */
	public void put(String option) {
		String[] parts = option.toLowerCase().split("=", 2);
		Option key = Option.get(parts[0]);
		String value;
		if (key == null || parts.length == 1 || !key.format().matches(value = parts[1]))
			Log.warning(INVALID_OPTION, option);
		else if (options.putIfAbsent(key, value) != null)
			Log.warning(DUPLICATED_OPTION, key);
	}

	/**
	 * Returns a Set object with the keys for this options.
	 *
	 * @return a set with this options' keys
	 */
	public Set<Option> keys() {
		return options.keySet();
	}

	/**
	 * Returns the value and removes the association for the given {@link Option}.
	 *
	 * @param option the option to be removed
	 *
	 * @return the value associated to the option
	 */
	public String remove(Option option) {
		String value = options.remove(option);
		String _default = option._default();
		if (value == null && _default != null) {
			Log.info(DEFAULT, _default, option);
			return _default;
		}
		return value;
	}

}
