package flow.algorithm.mst;

import cli.Options;
import data.Context;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class MinimumSpanningTree extends Algorithm {

	protected MinimumSpanningTree(Context context, Options options) {
		super(context, options);
	}

	@Override
	protected final void reduce(Pair<Double, Double> distances) {
		// ignore this step
	}

}
