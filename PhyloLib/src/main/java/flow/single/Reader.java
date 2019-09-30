package flow.single;

import exception.ParameterException;
import read.ConsoleReader;
import read.FileReader;
import read.IReader;
import read.LinkReader;

import java.util.HashMap;
import java.util.List;

public class Reader extends SingleComponent<IReader> {

	public Reader(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-reader", 1, null, new HashMap<>() {{
			put("console", new ConsoleReader());
			put("file", new FileReader());
			put("link", new LinkReader());
		}});
	}

	public String read() {
		return option.read(values.get(0));
	}

}
