package flow;

import exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Component {

	protected Component(List<String> values, String name, int number) throws NumberOfArgumentsException {
		if (values.size() - 1 != number)
			throw new NumberOfArgumentsException(name, values.get(0), number, values.size() - 1);
	}

	public interface Creator<T> {
		T create(List<String> values) throws NumberOfArgumentsException;
	}

	public static <T> T getSingle(HashMap<String, List<List<String>>> parameters, String name,
								  List<String> defaults, HashMap<String, Creator<T>> options) throws ParameterException {
		List<List<String>> params = parameters.getOrDefault(name, new ArrayList<>());
		if (params.size() > 1)
			throw new RepeatedParameterException(name);
		List<String> values = params.isEmpty() ? defaults : params.get(0);
		if (values == null)
			throw new MandatoryParameterException(name);
		return get(name, options, values);
	}

	public static <T> List<T> getMultiple(HashMap<String, List<List<String>>> parameters, String name,
										  HashMap<String, Creator<T>> options) throws ParameterException {
		List<T> functions = new ArrayList<>();
		for (List<String> values : parameters.getOrDefault(name, new ArrayList<>()))
			functions.add(get(name, options, values));
		return functions;
	}

	private static <T> T get(String name, HashMap<String, Creator<T>> options, List<String> values) throws ParameterException {
		String value = values.get(0);
		Creator<T> option = options.get(value);
		if (option == null)
			throw new InvalidTypeParameterException(name, value);
		return option.create(values);
	}

}
