package flow.single;

import exception.*;
import flow.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class SingleComponent<T> implements Component {

	final List<String> values;
	final T option;

	SingleComponent(HashMap<String, List<List<String>>>parameters, String name, int number,
					List<String> defaults, HashMap<String, T> options) throws ParameterException {
		List<List<String>> params = parameters.getOrDefault(name, new ArrayList<>());
		if (params.size() > 1)
			throw new RepeatedParameterException(name);
		List<String> values = params.get(0);
		if (values == null) {
			if (defaults == null)
				throw new MandatoryParameterException(name);
			values = defaults;
		} else if (values.size() < number + 1)
			throw new NumberOfArgumentsException(name, number + 1);
		String value = values.remove(0);
		T option = options.get(value);
		if (option == null)
			throw new InvalidTypeParameterException(name, value);
		this.values = values;
		this.option = option;
	}

}
