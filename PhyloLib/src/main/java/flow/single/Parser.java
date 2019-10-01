package flow.single;

import data.DataSet;
import exception.ParameterException;
import parse.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser extends SingleComponent<IParser> {

	public Parser(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-parser", 0, new ArrayList<>(){{ add("mlst"); }}, new HashMap<>() {{
			put("mlst", new MLSTParser());
			put("snp", new SNPParser());
			put("mlva", new MLVAParser());
			put("fasta", new FASTAParser());
		}});
	}

	public DataSet parse(String data) {
		return option.parse(data);
	}

}
