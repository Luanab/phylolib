package flow.optimize;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UnrootedOptimizer extends Optimizer {

	public UnrootedOptimizer(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree) {
		return null;
	}

}
