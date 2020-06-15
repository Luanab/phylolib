package cli;

import logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Options {

	private static final String DEFAULT = "Used default value '%s' for option %s";
	private static final String INVALID_OPTION = "Ignored invalid option '%s'";
	private static final String DUPLICATED_OPTION = "Ignored duplicated option %s";

	private final Map<Option, String> options = new HashMap<>();

	public void put(String option) {
		String[] parts = option.toLowerCase().split("=", 2);
		Option key = Option.get(parts[0]);
		String value;
		if (key == null || parts.length == 1 || !key.format().matches(value = parts[1]))
			Log.warning(INVALID_OPTION, option);
		else if (options.putIfAbsent(key, value) != null)
			Log.warning(DUPLICATED_OPTION, key);
	}

	public Set<Option> keys() {
		return options.keySet();
	}

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
