package flow.process.nj;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class FastNeighbourJoining extends NeighbourJoiningAlgorithm {

	protected FastNeighbourJoining(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	public Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

	@Override
	protected void reduce(DistanceMatrix matrix, Pair<Double, Double> distances) {

	}

}
