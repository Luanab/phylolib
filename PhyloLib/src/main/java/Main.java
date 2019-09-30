import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.ParameterException;
import flow.single.*;
import flow.multiple.Optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		HashMap<String, List<List<String>>> parameters = new HashMap<>();
		List<String> current = new ArrayList<>();
		for (String arg : args) {
			if (arg.startsWith("-")) {
				current = new ArrayList<>();
				parameters.putIfAbsent(arg, new ArrayList<>());
				parameters.get(arg).add(current);
			} else
				current.add(arg);
		}
		try {
			Reader reader = new Reader(parameters);
			Parser parser = new Parser(parameters);
			DistanceCalculator calculator = new DistanceCalculator(parameters);
			Algorithm algorithm = new Algorithm(parameters);
			Optimizer optimizer = new Optimizer(parameters);
			Formatter formatter = new Formatter(parameters);
			Writer writer = new Writer(parameters);

			String data = reader.read();
			DataSet dataset = parser.parse(data);
			DistanceMatrix matrix = calculator.calculate(dataset);
			PhylogeneticTree tree = algorithm.process(matrix);
			tree = optimizer.optimize(dataset, matrix, tree);
			String formatted = formatter.format(tree);
			writer.write(formatted);
		} catch (ParameterException e) {
			System.out.println(e.getMessage());
			//System.out.println("How to use");
		}
	}

}
