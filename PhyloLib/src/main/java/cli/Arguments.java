package cli;

import exception.InvalidOptionFormatException;
import exception.MissingOptionValueException;
import exception.NoCommandException;
import exception.RepeatedOptionException;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class Arguments extends HashMap<String, List<Parameters>> {

	private static final String[] COMMANDS = { Distance.NAME, Correction.NAME, Algorithm.NAME, Optimization.NAME };

	public boolean parse(String[] args) throws NoCommandException, InvalidOptionFormatException, MissingOptionValueException, RepeatedOptionException {
		if (args.length == 0)
			throw new NoCommandException();
		if (args[0].toLowerCase().matches("^(-h|--help)$"))
			return false;
		for (int i = 0; i < args.length; i++) {
			String command = args[i++].toLowerCase();
			String type = args[i++].toLowerCase();
			Options options = new Options();
			while (i < args.length && !args[i].equals(":")) {
				String[] option = args[i++]
						.toLowerCase()
						.replace("\"", "")
						.split("=", 2);
				String name = option[0], value;
				if (!name.matches("^(-.|--.*)$"))
					throw new InvalidOptionFormatException(name);
				if (option.length == 1 || (value = option[1]).isBlank())
					throw new MissingOptionValueException(name);
				if (options.put(name, value).isPresent())
					throw new RepeatedOptionException(name);
			}
			if (!Arrays.asList(COMMANDS).contains(command))
				Logger.warning("Ignoring invalid command '" + command + "'");
			else {
				putIfAbsent(command, new ArrayList<>());
				get(command).add(new Parameters(type, options));
			}
		}
		if (isEmpty())
			throw new NoCommandException();
		return true;
	}

}
