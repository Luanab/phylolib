package flow;

import data.DataSet;
import data.PhylogeneticTree;
import exception.parameter.ParameterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

		Supplier<String> reader = (new Reader()).get(parameters);
		Function<String, DataSet> parser = (new Parser()).get(parameters);
		Function<DataSet, PhylogeneticTree> algorithm = (new Algorithm()).get(parameters);
		Function<PhylogeneticTree, String> formatter = (new Formatter()).get(parameters);
		Consumer<String> writer = (new Writer()).get(parameters);

		String data = reader.get();
		DataSet dataset = parser.apply(data);
		PhylogeneticTree tree = algorithm.apply(dataset);
		String formatted = formatter.apply(tree);
		writer.accept(formatted);
	}

}
