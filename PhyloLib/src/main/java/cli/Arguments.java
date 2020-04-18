package cli;

import command.Command;
import exception.*;

import java.util.*;
import java.util.stream.Collectors;

public final class Arguments extends HashMap<String, List<Parameters>> {

	private static final Set<String> COMMANDS = Arrays.stream(Command.values()).map(Command::getName).collect(Collectors.toSet());
	private static final String HELP = "help";
	private static final String SEPARATOR = ":";

	public static Arguments parse(String[] args) throws NoCommandException, InvalidCommandException, RepeatedCommandException, MissingTypeException, InvalidTypeException {
		if (args.length == 0)
			throw new NoCommandException();
		if (args[0].equals(HELP))
			return null;
		Arguments arguments = new Arguments();
		for (int i = 0; i < args.length; i++) {
			String command = args[i++].toLowerCase().trim();
			if (!COMMANDS.contains(command))
				throw new InvalidCommandException(command);
			if (arguments.containsKey(command) && !Command.get(command).isRepeatable())
				throw new RepeatedCommandException(command);
			if (i == args.length || args[i].startsWith("-") || args[i].equals(SEPARATOR))
				throw new MissingTypeException(command);
			String type = args[i++].toLowerCase();
			if (!Command.get(command).hasType(type))
				throw new InvalidTypeException(command, type);
			Options options = new Options();
			while (i < args.length && !args[i].equals(SEPARATOR))
				options.put(args[i++]);
			arguments.computeIfAbsent(command, k -> new ArrayList<>()).add(new Parameters(type, options));
		}
		return arguments;
	}

}
