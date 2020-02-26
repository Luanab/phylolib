package flow.algorithm.nj;

import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class BIONJ extends NeighbourJoining {

	public BIONJ(Context context, Options options) {
		super(context, options);
	}

	@Override
	protected Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	protected void reduce(Pair<Double, Double> distances) {

	}

}
