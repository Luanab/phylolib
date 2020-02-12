package cli;

import exception.InvalidOptionFormatException;
import exception.MissingOptionValueException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arguments extends HashMap<String, List<Parameters>> {

    public void parse(String[] args) throws InvalidOptionFormatException, MissingOptionValueException {
        for (int i = 0; i < args.length; i++) {
            String command = args[i++].toLowerCase();
            String type = args[i++].toLowerCase();
            Options options = new Options();
            putIfAbsent(command, new ArrayList<>());
            get(command).add(new Parameters(type, options));
            while (i < args.length && !args[i].equals(":")) {
                String[] option = args[i++]
                        .toLowerCase()
                        .replace("\"", "")
                        .split("=", 2);
                String name = option[0], value = option[1];
                if (!name.matches("^(-.|--.*)$"))
                    throw new InvalidOptionFormatException(name);
                if (value.isEmpty())
                    throw new MissingOptionValueException(command, name);
                options.put(name, value);
            }
        }
    }

}
