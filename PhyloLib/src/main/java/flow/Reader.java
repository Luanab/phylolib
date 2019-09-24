package flow;

import exception.ParameterException;
import read.ConsoleReader;
import read.FileReader;
import read.IReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Reader extends Component<IReader> {

	Reader(HashMap<String, List<String>> parameters) throws ParameterException {
		super(parameters, "-reader", 1, new ArrayList<>(){{ add("console"); add(null); }}, new HashMap<>() {{
			put("console", new ConsoleReader());
			put("file", new FileReader());
		}});
	}

	String read() {
		return option.read(values.get(0));
	}

}
