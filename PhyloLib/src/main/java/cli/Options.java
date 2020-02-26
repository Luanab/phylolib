package cli;

import exception.InvalidFormatException;
import exception.MissingOptionValueException;
import exception.RepeatedOptionException;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public final class Options {

	private final HashMap<String, String> options = new HashMap<>();

	public void put(String option) throws InvalidFormatException, MissingOptionValueException, RepeatedOptionException {
		String[] parts = option.toLowerCase().split("=", 2);
		String key = parts[0], value;
		if (!key.startsWith("-"))
			throw new InvalidFormatException(key);
		if (parts.length == 1 || (value = parts[1]).isBlank())
			throw new MissingOptionValueException(key);
		if (options.put(key, value) != null)
			throw new RepeatedOptionException(key);
	}

	public String remove(String key, char alias, Format format, String _default) throws InvalidFormatException {
		Optional<String> value = remove(key, alias, format);
		if (value.isEmpty())
			Logger.info("Using default value '" + _default + "' for option --" + key);
		return value.orElse(_default);
	}

	public Optional<String> remove(String key, char alias, Format format) throws InvalidFormatException {
		Optional<String> result = remove("--" + key).or(() -> remove("-" + alias));
		if (result.isPresent() && !result.get().matches(format.regex))
			throw new InvalidFormatException(result.get());
		return result;
	}

	private Optional<String> remove(String key) {
		return Optional.ofNullable(options.remove(key));
	}

	public Set<String> keys() {
		return options.keySet();
	}

}
