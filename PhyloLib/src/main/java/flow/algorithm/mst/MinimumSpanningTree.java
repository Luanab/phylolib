package flow.algorithm.mst;

import data.Context;
import data.tree.Pair;
import flow.algorithm.Algorithm;

import java.util.HashMap;

public abstract class MinimumSpanningTree extends Algorithm {

    MinimumSpanningTree(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }


    @Override
    protected final void reduce(Pair<Double, Double> distances) {
        // ignore this step
    }

}
