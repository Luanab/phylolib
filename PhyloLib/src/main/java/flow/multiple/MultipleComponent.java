package flow.multiple;

import exception.InvalidTypeParameterException;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class MultipleComponent<T> implements Component {

	final List<List<String>> values = new ArrayList<>();
	final List<T> options = new ArrayList<>();

	MultipleComponent(HashMap<String, List<List<String>>> parameters, String name, int number,
					  HashMap<String, T> options) throws ParameterException {
		for (List<String> values : parameters.getOrDefault(name, new ArrayList<>())) {
			if (values.size() < number + 1)
				throw new NumberOfArgumentsException(name, number + 1);
			String value = values.remove(0);
			T option = options.get(value);
			if (option == null)
				throw new InvalidTypeParameterException(name, value);
			this.values.add(values);
			this.options.add(option);
		}
	}

}
