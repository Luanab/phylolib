package cli;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public final class Options {

	private final HashMap<String, String> options = new HashMap<>();

	public Optional<String> put(String key, String value) {
		return Optional.ofNullable(options.put(key, value));
	}

	public String remove(String key, char alias, String _default) {
		Optional<String> value = remove(key, alias);
		if (!value.isPresent())
			Logger.info("Using default value '" + _default + "' for option --" + key);
		return value.orElse(_default);
	}

	public Optional<String> remove(String key, char alias) {
		return remove("--" + key).or(() -> remove("-" + alias));
	}

	private Optional<String> remove(String key) {
		return Optional.ofNullable(options.remove(key));
	}

	public Set<String> keys() {
		return options.keySet();
	}

}
