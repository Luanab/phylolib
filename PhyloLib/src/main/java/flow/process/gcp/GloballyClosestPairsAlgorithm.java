package flow.process.gcp;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class GloballyClosestPairsAlgorithm extends Algorithm {

	GloballyClosestPairsAlgorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	@Override
	protected final Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

	@Override
	protected final Pair<Double, Double> join(DistanceMatrix matrix, Pair<Cluster, Cluster> clusters) {
		return null;
	}

}
