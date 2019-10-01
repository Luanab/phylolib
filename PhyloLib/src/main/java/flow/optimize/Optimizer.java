package flow.optimize;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;

import java.util.HashMap;
import java.util.List;

public abstract class Optimizer extends Component {

	private static final String NAME = "-optimizer";

	protected Optimizer(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree);

	public static List<Optimizer> get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getMultiple(parameters, NAME, new HashMap<>() {{
			put("rooted", RootedOptimizer::new);
			put("unrooted", UnrootedOptimizer::new);
		}});
	}

}
