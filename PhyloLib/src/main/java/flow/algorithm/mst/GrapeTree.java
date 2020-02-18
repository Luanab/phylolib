package flow.algorithm.mst;

import cli.Parameters;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class GrapeTree extends MinimumSpanningTree {

	public GrapeTree(Context context, Parameters parameters) {
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

}
