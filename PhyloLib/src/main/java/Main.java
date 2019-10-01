import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.ParameterException;
import flow.algorithm.Algorithm;
import flow.distance.DistanceCalculator;
import flow.format.Formatter;
import flow.optimization.Optimizer;
import flow.parse.Parser;
import flow.read.Reader;
import flow.write.Writer;

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
			Reader reader = Reader.get(parameters);
			Parser parser = Parser.get(parameters);
			DistanceCalculator calculator = DistanceCalculator.get(parameters);
			Algorithm algorithm = Algorithm.get(parameters);
			List<Optimizer> optimizers = Optimizer.get(parameters);
			Formatter formatter = Formatter.get(parameters);
			Writer writer = Writer.get(parameters);

			String data = reader.read();
			DataSet dataset = parser.parse(data);
			DistanceMatrix matrix = calculator.calculate(dataset);
			PhylogeneticTree tree = algorithm.process(matrix);
			for (Optimizer option : optimizers)
				tree = option.optimize(dataset, matrix, tree);
			String formatted = formatter.format(tree);
			writer.write(formatted);
		} catch (ParameterException e) {
			System.out.println(e.getMessage());
			//System.out.println("How to use");
		}
	}

}
