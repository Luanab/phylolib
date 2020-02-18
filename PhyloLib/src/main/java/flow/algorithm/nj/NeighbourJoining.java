package flow.algorithm.nj;

import cli.Parameters;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class NeighbourJoining extends Algorithm {

	protected NeighbourJoining(Context context, Parameters parameters) {
		super(context, parameters);
	}

	@Override
	protected Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
		return null;
	}

	@Override
	protected void reduce(Pair<Double, Double> distances) {

	}

}
