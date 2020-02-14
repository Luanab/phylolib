package cli;

import java.util.HashMap;
import java.util.Optional;

public class Options {

    private final HashMap<String, String> options = new HashMap<>();

    public void put(String key, String value) {
        options.put(key, value);
    }

    public Optional<String> get(String key, char alias) {
        return Optional.ofNullable(options.getOrDefault("--" + key, options.get("-" + alias)));
    }

}
