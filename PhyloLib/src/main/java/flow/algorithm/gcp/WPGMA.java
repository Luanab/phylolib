package flow.algorithm.gcp;

import cli.Options;
import data.Context;
import data.tree.Pair;

public final class WPGMA extends GloballyClosestPairs {

	public WPGMA(Context context, Options options) {
		super(context, options);
	}

	@Override
	protected void reduce(Pair<Double, Double> distances) {

	}

}
