package flow.optimize;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.ParameterException;
import flow.Parameters;

import java.util.HashMap;
import java.util.List;

public abstract class Optimizer {

	public abstract PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree);

	public static List<Optimizer> get(Parameters parameters) throws ParameterException {
		return parameters.map("-optimizer", "-o", new HashMap<>() {{ }});
	}

}
