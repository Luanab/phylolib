package flow.parse;

import data.DataSet;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Parser extends Component {

	private static final String NAME = "-parser";

	protected Parser(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract DataSet parse(String data);

	public static Parser get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, new ArrayList<>(){{ add("mlst"); }}, new HashMap<>() {{
			put("mlst", MLSTParser::new);
			put("snp", SNPParser::new);
			put("mlva", MLVAParser::new);
			put("fasta", FASTAParser::new);
		}});
	}

}
