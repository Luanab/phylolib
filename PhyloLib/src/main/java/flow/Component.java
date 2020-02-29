package flow;

import cli.Arguments;
import cli.Logger;
import cli.Options;
import cli.Parameters;
import data.Context;
import exception.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Component<T> {

	protected final Context context;
	private final Options options;
	private final IWriter<T> output;
	protected List<IReader> inputs;

	protected Component(Context context, Options options, IReader input, IWriter<T> output) {
		this.context = context;
		this.options = options;
		this.inputs = new ArrayList<>() {{add(input);}};
		this.output = output;
	}

	public static <T extends Component<?>> void run(Arguments arguments, Context context, String command, boolean single, HashMap<String, IConstructor<T>> constructors)
			throws RepeatedCommandException, InvalidTypeException, InvalidFileException, InvalidFormatException, MissingInputException, IOException {
		List<Parameters> commands = arguments.getOrDefault(command, new ArrayList<>());
		if (single && commands.size() > 1)
			throw new RepeatedCommandException(command);
		for (Parameters parameters : commands) {
			String type = parameters.getType();
			IConstructor<T> constructor = constructors.get(type);
			if (constructor == null)
				throw new InvalidTypeException(command, type);
			Logger.info("Command '" + command + "' with type '" + type + "' started running");
			constructor.construct(context, parameters.getOptions()).run();
			Logger.info("Command '" + command + "' with type '" + type + "' finished running");
		}
	}

	protected abstract T process();

	public final void run() throws InvalidFileException, InvalidFormatException, MissingInputException, IOException {
		for (IReader input : inputs)
			input.read(options);
		T result = process();
		output.write(options, result);
		options.keys().forEach(option -> Logger.warning("Ignoring invalid option '" + option + "'"));
	}

}
