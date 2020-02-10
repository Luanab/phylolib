package flow.algorithm.gcp;


import cli.Options;
import data.Context;
import data.tree.Pair;

public final class UPGMA extends GloballyClosestPairs {

    public UPGMA(Context context, Options options) throws Exception {
        super(context, options);
    }


    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
