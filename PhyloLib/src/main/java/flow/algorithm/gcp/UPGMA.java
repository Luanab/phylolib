package flow.algorithm.gcp;


import cli.Parameters;
import data.Context;
import data.tree.Pair;

public final class UPGMA extends GloballyClosestPairs {

	public UPGMA(Context context, Parameters parameters) {
		super(context, parameters);
	}

	@Override
	protected void reduce(Pair<Double, Double> distances) {

	}

}
