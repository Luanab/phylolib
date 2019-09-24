package process.algorithm.nj;

import data.*;
import process.algorithm.IAlgorithm;

abstract class Base implements IAlgorithm {

	@Override
	public PhylogeneticTree process(DataSet dataset) {
		return null;
	}

	protected abstract Pair<Cluster, Cluster> select(DistanceMatrix matrix);
	protected abstract void join();
	protected abstract void reduct();

}
