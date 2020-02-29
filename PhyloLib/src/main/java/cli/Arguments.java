package cli;

import exception.*;
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

	public static Arguments parse(String[] args) throws NoCommandException, MissingTypeException, InvalidFormatException, MissingOptionValueException, RepeatedOptionException {
		if (args.length == 0)
			throw new NoCommandException();
		if (args[0].equals("-h") || args[0].equals("--help"))
			return null;
		Arguments arguments = new Arguments();
		for (int i = 0; i < args.length; i++) {
			String command = args[i++].toLowerCase();
			if (Arrays.asList(COMMANDS).contains(command)) {
				if (i == args.length || args[i].startsWith("-") || args[i].equals(":"))
					throw new MissingTypeException(command);
				String type = args[i++].toLowerCase();
				Options options = new Options();
				while (i < args.length && !args[i].equals(":"))
					options.put(args[i++]);
				arguments.computeIfAbsent(command, k -> new ArrayList<>()).add(new Parameters(type, options));
			} else {
				Logger.warning("Ignoring invalid command '" + command + "'");
				while (i < args.length && !args[i].equals(":"))
					i++;
			}
		}
		if (arguments.isEmpty())
			throw new NoCommandException();
		return arguments;
	}

}
