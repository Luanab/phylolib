package flow.algorithm.mst;

import cli.Parameters;
import data.Context;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class MinimumSpanningTree extends Algorithm {

    protected MinimumSpanningTree(Context context, Parameters parameters) {
        super(context, parameters);
    }

    protected MinimumSpanningTree(Context context, Parameters parameters, boolean dataset, boolean matrix, boolean tree) {
        super(context, parameters, dataset, matrix, tree);
    }

    @Override
    protected final void reduce(Pair<Double, Double> distances) {
        // ignore this step
    }

}
