package cli;

import java.util.HashMap;

public class Options extends HashMap<String, String> {

    private static String full(Object key) {
        return "--" + key.toString();
    }

    private static String alias(Object key) {
        return "-" + key.toString().charAt(0);
    }

    @Override
    public String get(Object key) {
        return super.getOrDefault(full(key), super.get(alias(key)));
    }

    @Override
    public String getOrDefault(Object key, String value) {
        return super.getOrDefault(full(key), super.getOrDefault(alias(key), value));
    }

}
