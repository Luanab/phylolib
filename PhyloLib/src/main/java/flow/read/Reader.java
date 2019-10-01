package flow.read;

import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;

import java.util.HashMap;
import java.util.List;

public abstract class Reader extends Component {

	private static final String NAME = "-reader";

	protected Reader(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract String read();

	public static Reader get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, null, new HashMap<>() {{
			put("console", ConsoleReader::new);
			put("file", FileReader::new);
			put("link", LinkReader::new);
		}});
	}

}
