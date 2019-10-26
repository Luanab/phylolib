package flow.process.nj;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UNJAlgorithm extends NeighbourJoiningAlgorithm {

	protected UNJAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected Pair<Double, Double> join(DistanceMatrix matrix, Pair<Cluster, Cluster> clusters) {
		return null;
	}

	@Override
	protected void reduce(DistanceMatrix matrix, Pair<Double, Double> distances) {

	}

}
