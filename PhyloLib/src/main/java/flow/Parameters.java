package flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parameters {

    private static final String DIVIDER = ":";

    private final HashMap<String, List<HashMap<String, String>>> parameters;

    public Parameters(String[] args) throws Exception {
        this.parameters = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String command = args[i++].toLowerCase();
            HashMap<String, String> current = new HashMap<>();
            parameters.putIfAbsent(command, new ArrayList<>());
            parameters.get(command).add(current);
            while (i < args.length && !args[i].equals(DIVIDER)) {
                if (i + 1 == args.length)
                    throw new Exception("Key value parameter violation...");
                current.put(args[i++].toLowerCase(), args[i++].toLowerCase());
            }
        }
    }

    public boolean contains(String key) {
        return parameters.containsKey(key);
    }

    public List<HashMap<String, String>> get(String key) {
        return parameters.get(key);
    }

}
