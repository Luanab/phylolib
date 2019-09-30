package flow.multiple;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.ParameterException;
import optimization.IOptimizer;
import optimization.RootedOptimizer;
import optimization.UnrootedOptimizer;

import java.util.HashMap;
import java.util.List;

public class Optimizer extends MultipleComponent<IOptimizer> {

	public Optimizer(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-optimizer", 0, new HashMap<>() {{
			put("rooted", new RootedOptimizer());
			put("unrooted", new UnrootedOptimizer());
		}});
	}

	public PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree) {
		for (IOptimizer option : options)
			tree = option.optimize(dataset, matrix, tree);
		return tree;
	}

}
