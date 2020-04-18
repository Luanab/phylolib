package command;

import cli.Arguments;
import cli.Options;
import cli.Parameters;
import data.Context;
import exception.MissingInputException;
import logging.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public interface ICommand<T, R> {

	String RUNNING = "%s running command '%s' with type '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String INVALID_OPTION = "Ignoring invalid option '%s'";

	static <T, R> void run(Arguments arguments, Context context, Command command, IGetter<T> getter, BiConsumer<Options, R> setter) throws MissingInputException, IllegalAccessException, InvocationTargetException, InstantiationException {
		for (Parameters parameters : arguments.getOrDefault(command.getName(), new ArrayList<>())) {
			ICommand<T, R> component = (ICommand<T, R>) command.getType(parameters.getType()).newInstance();
			Log.info(RUNNING, STARTED, command.getName(), parameters.getType());
			Options options = parameters.getOptions();
			T data = getter.get(options);
			component.init(context, options);
			R result = component.process(data);
			setter.accept(options, result);
			options.keys().forEach(option -> Log.warning(INVALID_OPTION, option));
			Log.info(RUNNING, FINISHED, command.getName(), parameters.getType());
		}
	}

	default void init(Context context, Options options) throws MissingInputException { }

	R process(T data);

	interface IGetter<T> {

		T get(Options options) throws MissingInputException;

	}

}
