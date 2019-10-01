package flow.algorithm.nj;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;
import flow.algorithm.DistanceMatrixAlgorithm;

import java.util.List;

public final class NeighbourJoiningAlgorithm extends DistanceMatrixAlgorithm {

	public NeighbourJoiningAlgorithm(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

	@Override
	public void join() {

	}

	@Override
	public void reduce() {

	}

}
