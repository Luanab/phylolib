package command;

import cli.Arguments;
import cli.Command;
import cli.Options;
import cli.Parameters;
import data.Context;
import exception.MissingInputException;
import logging.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public interface ICommand<T, R> {

	String COMMAND = "%s running command '%s' with type '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String UNUSED = "Ignored unused option '%s'";

	@SuppressWarnings("unchecked")
	static <T, R> void run(Arguments arguments, Context context, Command command, IGetter<T> getter, BiConsumer<Options, R> setter) throws MissingInputException, IllegalAccessException, InvocationTargetException, InstantiationException {
		for (Parameters parameters : arguments.getOrDefault(command, new ArrayList<>())) {
			ICommand<T, R> component = (ICommand<T, R>) parameters.type().newInstance();
			Log.info(COMMAND, STARTED, command, parameters.type());
			Options options = parameters.options();
			T data = getter.get(options);
			component.init(context, options);
			R result = component.process(data);
			setter.accept(options, result);
			options.keys().forEach(option -> Log.warning(UNUSED, option));
			Log.info(COMMAND, FINISHED, command, parameters.type());
		}
	}

	default void init(Context context, Options options) throws MissingInputException { }

	R process(T data);

	interface IGetter<T> {

		T get(Options options) throws MissingInputException;

	}

}
