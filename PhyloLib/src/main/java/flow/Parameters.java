package flow;

import exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parameters {

	private final HashMap<String, List<List<String>>> parameters;

	public Parameters(String[] args) {
		this.parameters = new HashMap<>();
		List<String> current = new ArrayList<>();
		for (String arg : args) {
			if (arg.startsWith("-")) {
				current = new ArrayList<>();
				parameters.putIfAbsent(arg, new ArrayList<>());
				parameters.get(arg).add(current);
			} else
				current.add(arg);
		}
	}

	public interface Option<T> {
		T init(String name, String value, List<String> parameters) throws NumberOfArgumentsException;
	}

	public <T extends Component> T map(String name, String abbreviation, List<String> defaults, HashMap<String, Option<T>> options) throws ParameterException {
		List<List<String>> params = parameters.getOrDefault(name, parameters.getOrDefault(abbreviation, new ArrayList<>()));
		if (params.size() > 1)
			throw new RepeatedParameterException(name);
		List<String> parameters = params.isEmpty() ? defaults : params.get(0);
		if (parameters == null)
			throw new MandatoryParameterException(name);
		return map(name, options, parameters);
	}

	public <T extends Component> List<T> map(String name, String abbreviation, HashMap<String, Option<T>> options) throws ParameterException {
		List<T> components = new ArrayList<>();
		for (List<String> parameters : parameters.getOrDefault(name, parameters.getOrDefault(abbreviation, new ArrayList<>())))
			components.add(map(name, options, parameters));
		return components;
	}

	private static <T extends Component> T map(String name, HashMap<String, Option<T>> options, List<String> parameters) throws ParameterException {
		String value = parameters.remove(0);
		Option<T> option = options.get(value);
		if (option == null)
			throw new InvalidTypeParameterException(name, value);
		return option.init(name, value, parameters);
	}

}
