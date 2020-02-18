package flow.algorithm.gcp;

import cli.Parameters;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class GloballyClosestPairs extends Algorithm {

	protected GloballyClosestPairs(Context context, Parameters parameters) {
		super(context, parameters);
	}

	@Override
	protected final Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	protected final Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
		return null;
	}

}
