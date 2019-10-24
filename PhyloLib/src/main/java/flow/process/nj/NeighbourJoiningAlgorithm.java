package flow.process.nj;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class NeighbourJoiningAlgorithm extends Algorithm {

	protected NeighbourJoiningAlgorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	@Override
	public Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

	@Override
	public Pair<Double, Double> join(DistanceMatrix matrix, Pair<Cluster, Cluster> clusters) {
		return null;
	}

	@Override
	protected void reduce(DistanceMatrix matrix, Pair<Double, Double> distances) {

	}

}
