package flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parameters {

    private static final String PREFIX = "-";

    private final HashMap<String, List<List<String>>> parameters;

    public Parameters(String[] args) {
        this.parameters = new HashMap<>();
        List<String> current = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i].trim().toLowerCase();
            if (arg.startsWith(PREFIX)) {
                current = new ArrayList<>();
                parameters.putIfAbsent(arg, new ArrayList<>());
                parameters.get(arg).add(current);
            } else
                current.add(arg);
        }
    }

    public boolean contains(String key) {
        return parameters.containsKey(PREFIX + key);
    }

    public List<List<String>> get(String key) {
        return parameters.get(PREFIX + key);
    }

}
