package flow.process.nj;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import flow.process.Algorithm;

public abstract class NeighbourJoiningAlgorithm extends Algorithm {

	private DistanceMatrix transformed;

	protected abstract double distance(DistanceMatrix matrix, int i, int j);

	@Override
	protected final void init() {
		int size = matrix.size();
		double[][] m = new double[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				m[i][j] = distance(transformed, i, j);
			}
		}
		this.transformed = new DistanceMatrix(m);
	}

	@Override
	public Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	public void join() {

	}

	@Override
	public void reduce() {

	}

}
