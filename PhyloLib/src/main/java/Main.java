import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.ParameterException;
import flow.Parameters;
import flow.process.Algorithm;
import flow.calculate.Calculator;
import flow.write.Writer;
import flow.optimize.Optimizer;
import flow.read.Reader;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		try {
			Parameters parameters = new Parameters(args);
			Reader reader = Reader.get(parameters);
			Calculator calculator = Calculator.get(parameters);
			Algorithm algorithm = Algorithm.get(parameters);
			List<Optimizer> optimizers = Optimizer.get(parameters);
			Writer writer = Writer.get(parameters);

			DataSet dataset = reader.read();
			DistanceMatrix matrix = calculator.calculate(dataset);
			PhylogeneticTree tree = algorithm.process(matrix);
			for (Optimizer optimizer : optimizers)
				tree = optimizer.optimize(dataset, matrix, tree);
			writer.write(tree);
		} catch (ParameterException | IOException e) {
			System.out.println(e.getMessage());
			//System.out.println("How to use");
		}
	}

}
