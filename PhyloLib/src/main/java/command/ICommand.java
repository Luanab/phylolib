package command;

import cli.*;
import data.Context;
import exception.MissingInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

public interface ICommand<T, R> {

	String RUNNING = "Command '%s' with type '%s' %s running";
	String STARTED = "started";
	String FINISHED = "finished";
	String DUPLICATED_COMMAND = "Ignoring duplicates of command '%s'";
	String INVALID_TYPE = "Ignoring command '%s' with invalid type '%s'";
	String INVALID_OPTION = "Ignoring invalid option '%s'";

	static <T, R> void run(Arguments arguments, Context context, Command command, IGetter<T> getter, BiConsumer<Options, R> setter, HashMap<String, ICommand<T, R>> map) throws MissingInputException {
		List<Parameters> commands = arguments.getOrDefault(command.getName(), new ArrayList<>());
		if (command.getGranularity() == Granularity.SINGLE && commands.size() > 1) {
			Logger.warning(DUPLICATED_COMMAND, command.getName());
			commands = commands.subList(0, 1);
		}
		for (Parameters parameters : commands) {
			String type = parameters.getType();
			ICommand<T, R> component = map.get(type);
			if (component == null)
				Logger.warning(INVALID_TYPE, command.getName(), type);
			else {
				Options options = parameters.getOptions();
				Logger.info(RUNNING, command.getName(), type, STARTED);
				T data = getter.get(options);
				component.init(context, options);
				R result = component.process(data);
				setter.accept(options, result);
				options.keys().forEach(option -> Logger.warning(INVALID_OPTION, option));
				Logger.info(RUNNING, command.getName(), type, FINISHED);
			}
		}
	}

	default void init(Context context, Options options) throws MissingInputException {

	}

	R process(T data);

	interface IGetter<T> {

		T get(Options options) throws MissingInputException;

	}

}
