package flow.write;

import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Writer extends Component {

	private static final String NAME = "-writer";

	protected Writer(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract void write(String data);

	public static Writer get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, new ArrayList<>(){{ add("console"); }}, new HashMap<>() {{
			put("console", ConsoleWriter::new);
			put("file", FileWriter::new);
		}});
	}

}
