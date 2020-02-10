package flow.algorithm.mst;

import cli.Parameters;
import data.Context;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class MinimumSpanningTree extends Algorithm {

    protected MinimumSpanningTree(Context context, Parameters parameters) {
        super(context, parameters);
    }

    @Override
    protected final void reduce(Pair<Double, Double> distances) {
        // ignore this step
    }

}
