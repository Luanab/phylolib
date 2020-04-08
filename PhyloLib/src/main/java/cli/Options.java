package cli;

import logging.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class Options {

	private static final String SEPARATOR = "=";
	private static final String ALIAS = "-";
	private static final String KEY = "--";
	private static final String DEFAULT = "Using default value '%s' for option -%c --%s";
	private static final String INVALID_OPTION = "Ignoring invalid option '%s'";
	private static final String DUPLICATED_OPTION = "Ignoring duplicated option '%s'";
	private static final String INVALID_VALUE = "Ignoring invalid value '%s' for option -%c --%s";

	private final Map<String, String> options = new HashMap<>();

	public void put(String option) {
		String[] parts = option.toLowerCase().split(SEPARATOR, 2);
		String key = parts[0], value;
		if (!key.startsWith(ALIAS) || parts.length == 1 || (value = parts[1]).isBlank())
			Log.warning(INVALID_OPTION, option);
		else if (options.putIfAbsent(key, value) != null)
			Log.warning(DUPLICATED_OPTION, option);
	}

	public Set<String> keys() {
		return options.keySet();
	}

	public String remove(Option option, String _default) {
		Optional<String> value = remove(option);
		if (value.isEmpty()) {
			Log.info(DEFAULT, _default, option.getAlias(), option.getKey());
			return _default;
		}
		return value.get();
	}

	public Optional<String> remove(Option option) {
		Optional<String> result = remove(KEY + option.getKey()).or(() -> remove(ALIAS + option.getAlias()));
		if (result.isPresent() && !option.getFormat().matches(result.get())) {
			Log.warning(INVALID_VALUE, result.get(), option.getAlias(), option.getKey());
			return Optional.empty();
		}
		return result;
	}

	private Optional<String> remove(String key) {
		return Optional.ofNullable(options.remove(key));
	}

}
