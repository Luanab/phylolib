package cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Commands extends HashMap<String, List<Parameters>> {

    public void init(String[] args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            String command = args[i++].toLowerCase();
            String type = args[i++].toLowerCase();
            Options options = new Options();
            putIfAbsent(command, new ArrayList<>());
            get(command).add(new Parameters(type, options));
            while (i < args.length && !args[i].equals(":")) {
                String[] parameter = args[i++]
                        .toLowerCase()
                        .replace("\"", "")
                        .split("=", 2);
                if (parameter[1].isEmpty())
                    throw new Exception("Key value parameter violation...");
                options.put(parameter[0], parameter[1]);
            }
        }
    }

}
