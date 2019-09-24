package flow;

import data.DataSet;
import exception.ParameterException;
import parse.CSVParser;
import parse.IParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Parser extends Component<IParser> {

	Parser(HashMap<String, List<String>> parameters) throws ParameterException {
		super(parameters, "-parser", 0, new ArrayList<>(){{ add("csv"); }}, new HashMap<>() {{
			put("csv", new CSVParser());
		}});
	}

	DataSet parse(String data) {
		return option.parse(data);
	}

}
