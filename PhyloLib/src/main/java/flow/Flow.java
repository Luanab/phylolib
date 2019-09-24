package flow;

import data.DataSet;
import data.PhylogeneticTree;
import exception.ParameterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flow {

	public static void process(String[] args) throws ParameterException {
		HashMap<String, List<String>> parameters = new HashMap<>();
		List<String> current = new ArrayList<>();
		for (String arg : args) {
			if (arg.startsWith("-")) {
				current = new ArrayList<>();
				parameters.put(arg, current);
			} else
				current.add(arg);
		}

		Reader reader = new Reader(parameters);
		Parser parser = new Parser(parameters);
		Algorithm algorithm = new Algorithm(parameters);
		Formatter formatter = new Formatter(parameters);
		Writer writer = new Writer(parameters);

		String data = reader.read();
		DataSet dataset = parser.parse(data);
		PhylogeneticTree tree = algorithm.process(dataset);
		String formatted = formatter.format(tree);
		writer.write(formatted);
	}

}
