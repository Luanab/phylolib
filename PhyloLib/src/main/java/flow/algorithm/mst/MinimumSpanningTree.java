package flow.algorithm.mst;

import cli.Options;
import data.Context;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class MinimumSpanningTree extends Algorithm {

    MinimumSpanningTree(Context context, Options options) throws Exception {
        super(context, options);
    }


    @Override
    protected final void reduce(Pair<Double, Double> distances) {
        // ignore this step
    }

}
