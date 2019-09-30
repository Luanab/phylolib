package flow.single;

import data.DataSet;
import exception.ParameterException;
import parse.CSVParser;
import parse.IParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser extends SingleComponent<IParser> {

	public Parser(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-parser", 0, new ArrayList<>(){{ add("csv"); }}, new HashMap<>() {{
			put("csv", new CSVParser());
		}});
	}

	public DataSet parse(String data) {
		return option.parse(data);
	}

}
