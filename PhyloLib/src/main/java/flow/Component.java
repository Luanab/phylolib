package flow;

import exception.InvalidTypeParameterException;
import exception.NumberOfArgumentsException;
import exception.ParameterException;

import java.util.HashMap;
import java.util.List;

abstract class Component<T> {

	final List<String> values;
	final T option;

	Component(HashMap<String, List<String>> parameters, String name, int number,
			  List<String> defaults, HashMap<String, T> options) throws ParameterException {
		this.values = parameters.getOrDefault(name, defaults);
		if (values.size() != number + 1)
			throw new NumberOfArgumentsException(name, number + 1);
		this.option = options.get(values.remove(0));
		if (this.option == null)
			throw new InvalidTypeParameterException(name);
	}

}
