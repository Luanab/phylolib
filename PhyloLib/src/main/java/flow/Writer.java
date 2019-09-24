package flow;

import exception.ParameterException;
import write.ConsoleWriter;
import write.FileWriter;
import write.IWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Writer extends Component<IWriter> {

	Writer(HashMap<String, List<String>> parameters) throws ParameterException {
		super(parameters, "-writer", 1, new ArrayList<>(){{ add("console"); add(null); }}, new HashMap<>() {{
			put("console", new ConsoleWriter());
			put("file", new FileWriter());
		}});
	}

	void write(String data) {
		option.write(data, values.get(0));
	}

}
