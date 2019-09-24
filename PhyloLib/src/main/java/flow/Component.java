package flow;

import exception.parameter.ParameterException;
import exception.parameter.InvalidTypeParameterException;
import exception.parameter.NumberOfArgumentsException;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

abstract class Component<T, R> {

	String name;
	int number;
	HashMap<String, T> options;
	BiFunction<List<String>, T, R> mapper;

	abstract List<String> defaultValues();

	R get(HashMap<String, List<String>> parameters) throws ParameterException {
		List<String> values = parameters.get(name);
		if (values == null)
			values = defaultValues();
		if (values.size() != number + 1)
			throw new NumberOfArgumentsException(name, number + 1);
		T option = options.get(values.remove(0));
		if (option == null)
			throw new InvalidTypeParameterException(name);
		return mapper.apply(values, option);
	}

}
