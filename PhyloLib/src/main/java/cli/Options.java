package cli;

import java.util.HashMap;

public class Options {

    private static final String FULL = "--", ALIAS = "-";

    private final HashMap<String, String> options = new HashMap<>();

    public void put(String name, String value) {
        options.put(name, value);
    }

    public boolean contains(String full, char alias) {
        return options.containsKey(FULL + full) || options.containsKey(ALIAS + alias);
    }

    public String get(String full, char alias) {
        return options.getOrDefault(FULL + full, options.get(ALIAS + alias));
    }

    public String get(String full, char alias, String value) {
        return options.getOrDefault(FULL + full, options.getOrDefault(ALIAS + alias, value));
    }

}
