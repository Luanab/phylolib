package flow.process.mst;

import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class MinimumSpanningTreeAlgorithm extends Algorithm {

	protected MinimumSpanningTreeAlgorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	@Override
	public final void reduce(DistanceMatrix matrix, Pair<Double, Double> distances) {
		// ignore this step
	}

}
