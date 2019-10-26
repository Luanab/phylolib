package flow.process.nj;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class SaitouNeiAlgorithm extends NeighbourJoiningAlgorithm {

	public SaitouNeiAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

	@Override
	protected void reduce(DistanceMatrix matrix, Pair<Double, Double> distances) {

	}

}
