package cli;

import logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class Options {

	private static final String DEFAULT = "Used default value '%s' for option --%s";
	private static final String INVALID_OPTION = "Ignored invalid option '%s'";
	private static final String DUPLICATED_OPTION = "Ignored duplicated option '%s'";

	private final Map<Option, String> options = new HashMap<>();

	public void put(String option) {
		String[] parts = option.toLowerCase().split("=", 2);
		Optional<Option> key = Option.get(parts[0]);
		String value;
		if (key.isEmpty() || parts.length == 1 || !key.get().getFormat().matches(value = parts[1]))
			Log.warning(INVALID_OPTION, option);
		else if (options.putIfAbsent(key.get(), value) != null)
			Log.warning(DUPLICATED_OPTION, option);
	}

	public Set<Option> keys() {
		return options.keySet();
	}

	public String remove(Option option, String _default) {
		Optional<String> value = remove(option);
		if (value.isEmpty()) {
			Log.info(DEFAULT, _default, option.toString());
			return _default;
		}
		return value.get();
	}

	public Optional<String> remove(Option option) {
		return Optional.ofNullable(options.remove(option));
	}

}
