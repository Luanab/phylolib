package flow.algorithm.gcp;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UPGMAAlgorithm extends GloballyClosestPairsAlgorithm {

	public UPGMAAlgorithm(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

}
