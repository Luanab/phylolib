package cli;

import command.Command;
import logging.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class Arguments extends HashMap<String, List<Parameters>> {

	private static final List<String> COMMANDS = Arrays.stream(Command.values()).map(Command::getName).collect(Collectors.toList());
	private static final String HELP = "help";
	private static final String SEPARATOR = ":";
	private static final String INVALID_COMMAND = "Ignoring invalid command '%s'";

	public static Arguments parse(String[] args) {
		Arguments arguments = new Arguments();
		if (args.length == 0)
			return arguments;
		if (args[0].equals(HELP))
			return null;
		for (int i = 0; i < args.length; i++) {
			String command = args[i++].toLowerCase();
			if (COMMANDS.contains(command) && i != args.length && !args[i].startsWith("-") && !args[i].equals(SEPARATOR)) {
				String type = args[i++].toLowerCase();
				Options options = new Options();
				while (i < args.length && !args[i].equals(SEPARATOR))
					options.put(args[i++]);
				arguments.computeIfAbsent(command, k -> new ArrayList<>()).add(new Parameters(type, options));
			} else {
				Log.warning(INVALID_COMMAND, command);
				while (i < args.length && !args[i].equals(SEPARATOR))
					i++;
			}
		}
		return arguments;
	}

}
