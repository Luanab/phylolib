package cli;

import java.util.HashMap;
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

	private final HashMap<String, String> options = new HashMap<>();

	public void put(String option) {
		String[] parts = option.toLowerCase().split(SEPARATOR, 2);
		String key = parts[0], value;
		if (!key.startsWith(ALIAS) || parts.length == 1 || (value = parts[1]).isBlank())
			Logger.warning(INVALID_OPTION, option);
		else if (options.putIfAbsent(key, value) != null)
			Logger.warning(DUPLICATED_OPTION, option);
	}

	public Set<String> keys() {
		return options.keySet();
	}

	public String remove(Option option, Format format, String _default) {
		Optional<String> value = remove(option, format);
		if (value.isEmpty()) {
			Logger.info(DEFAULT, _default, option.getAlias(), option.getKey());
			return _default;
		}
		return value.get();
	}

	public Optional<String> remove(Option option, Format format) {
		Optional<String> result = remove(KEY + option.getKey()).or(() -> remove(ALIAS + option.getAlias()));
		if (result.isPresent() && !format.matches(result.get())) {
			Logger.warning(INVALID_VALUE, result.get(), option.getAlias(), option.getKey());
			return Optional.empty();
		}
		return result;
	}

	private Optional<String> remove(String key) {
		return Optional.ofNullable(options.remove(key));
	}

}
