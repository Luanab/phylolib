package cli;

import exception.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Arguments extends HashMap<Command, List<Parameters>> {

	private static final String HELP = "help";
	private static final String SEPARATOR = ":";

	public static Arguments parse(String[] args) throws NoCommandException, InvalidCommandException, RepeatedCommandException, MissingTypeException, InvalidTypeException {
		if (args.length == 0)
			throw new NoCommandException();
		if (args[0].equals(HELP))
			return null;
		Arguments arguments = new Arguments();
		for (int i = 0; i < args.length; i++) {
			Command command = Command.get(args[i++]);
			if (command == null)
				throw new InvalidCommandException(args[i - 1]);
			if (arguments.containsKey(command) && !command.isRepeatable())
				throw new RepeatedCommandException(args[i - 1]);
			if (i == args.length || args[i].startsWith("-") || args[i].equals(SEPARATOR))
				throw new MissingTypeException(args[i - 1]);
			Constructor<?> type = command.type(args[i++]);
			if (type == null)
				throw new InvalidTypeException(args[i - 2], args[i - 1]);
			Options options = new Options();
			while (i < args.length && !args[i].equals(SEPARATOR))
				options.put(args[i++]);
			arguments.computeIfAbsent(command, k -> new ArrayList<>()).add(new Parameters(type, options));
		}
		return arguments;
	}

}
