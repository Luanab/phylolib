package process.algorithm;

import data.*;

public abstract class DistanceMatrixAlgorithm implements IAlgorithm {

	@Override
	public final PhylogeneticTree process(DistanceMatrix matrix) {
		return null;
	}

	protected abstract Pair<Cluster, Cluster> select(DistanceMatrix matrix);
	protected abstract void join();
	protected abstract void reduce();

}
