package process.algorithm.clusters;

import data.*;
import process.algorithm.IAlgorithm;

public abstract class HierarchicalClustersAlgorithm implements IAlgorithm {

	@Override
	public final PhylogeneticTree process(DataSet dataset) {
		return null;
	}

	protected abstract Pair<Cluster, Cluster> select(DistanceMatrix matrix);
	protected abstract void join();
	protected abstract void reduce();

}
