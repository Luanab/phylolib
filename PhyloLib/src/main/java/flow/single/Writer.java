package flow.single;

import exception.ParameterException;
import write.ConsoleWriter;
import write.FileWriter;
import write.IWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Writer extends SingleComponent<IWriter> {

	public Writer(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-writer", 1, new ArrayList<>(){{ add("console"); add(null); }}, new HashMap<>() {{
			put("console", new ConsoleWriter());
			put("file", new FileWriter());
		}});
	}

	public void write(String data) {
		option.write(data, values.get(0));
	}

}
