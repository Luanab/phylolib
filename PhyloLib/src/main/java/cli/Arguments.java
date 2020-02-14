package cli;

import exception.InvalidCommandException;
import exception.InvalidOptionFormatException;
import exception.MissingOptionValueException;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Arguments extends HashMap<String, List<Parameters>> {

    private static final String[] COMMANDS = {Distance.NAME, Correction.NAME, Algorithm.NAME, Optimization.NAME};

    public boolean parse(String[] args) throws InvalidCommandException, InvalidOptionFormatException, MissingOptionValueException {
        if (args.length == 0 || args[0].toLowerCase().matches("^(-h|--help)$"))
            return false;
        for (int i = 0; i < args.length; i++) {
            String command = args[i++].toLowerCase();
            if (!Arrays.asList(COMMANDS).contains(command))
                throw new InvalidCommandException(command);
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
        return true;
    }

}
