package flow.algorithm.mst;

import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class GrapeTree extends MinimumSpanningTree {

	public GrapeTree(Context context, Options options) {
		super(context, options);
	}

	@Override
	protected Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
		return null;
	}

}
