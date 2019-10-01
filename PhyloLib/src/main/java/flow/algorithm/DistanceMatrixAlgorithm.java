package flow.algorithm;

import data.*;
import exception.NumberOfArgumentsException;

import java.util.List;

public abstract class DistanceMatrixAlgorithm extends Algorithm {

	protected DistanceMatrixAlgorithm(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, number);
	}

	@Override
	public final PhylogeneticTree process(DistanceMatrix matrix) {
		return null;
	}

	protected abstract Pair<Cluster, Cluster> select(DistanceMatrix matrix);
	protected abstract void join();
	protected abstract void reduce();

}
