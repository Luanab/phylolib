package flow.algorithm.mst;

import flow.Pair;
import flow.algorithm.Algorithm;

import java.util.List;

public abstract class MinimumSpanningTree extends Algorithm {

    MinimumSpanningTree(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next);
    }

    @Override
    protected final void reduce(Pair<Double, Double> distances) {
        // ignore this step
    }

}
