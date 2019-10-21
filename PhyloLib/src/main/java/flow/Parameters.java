package flow;

import exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parameters {

	private final HashMap<String, List<List<String>>> parameters;

	public final String from;
	public final String to;

	public Parameters(String[] args) throws MandatoryParameterException {
		if (args.length < 1)
			throw new MandatoryParameterException("from");
		if (args.length < 2)
			throw new MandatoryParameterException("to");
		this.from = args[0];
		this.to = args[1];
		this.parameters = new HashMap<>();
		List<String> current = new ArrayList<>();
		for (int i = 2; i < args.length; i++) {
			String arg = args[i];
			if (arg.startsWith("-")) {
				current = new ArrayList<>();
				parameters.putIfAbsent(arg, new ArrayList<>());
				parameters.get(arg).add(current);
			} else
				current.add(arg);
		}
	}

	public <T> T map(String name, String abbreviation, List<String> defaults, HashMap<String, Option<T>> options) throws ParameterException {
		List<List<String>> params = parameters.getOrDefault(name, new ArrayList<>());
		params.addAll(parameters.get(abbreviation));
		if (params.size() > 1)
			throw new RepeatedParameterException(name);
		List<String> values = params.isEmpty() ? defaults : params.get(0);
		if (values == null)
			throw new MandatoryParameterException(name);
		return map(name, options, values);
	}

	public <T> List<T> map(String name, String abbreviation, HashMap<String, Option<T>> options) throws ParameterException {
		List<List<String>> params = parameters.getOrDefault(name, new ArrayList<>());
		params.addAll(parameters.get(abbreviation));
		List<T> functions = new ArrayList<>();
		for (List<String> values : params)
			functions.add(map(name, options, values));
		return functions;
	}

	private static <T> T map(String name, HashMap<String, Option<T>> options, List<String> values) throws ParameterException {
		String value = values.remove(0);
		Option<T> option = options.get(value);
		if (option == null)
			throw new InvalidTypeParameterException(name, value);
		int number = option.parameters;
		if (values.size() != number)
			throw new NumberOfArgumentsException(name, value, number, values.size());
		return option.creator.apply(values);
	}

}
