package flow.algorithm.gcp;

import data.tree.Cluster;
import flow.Pair;
import flow.algorithm.Algorithm;

import java.util.List;

public abstract class GloballyClosestPairs extends Algorithm {

    GloballyClosestPairs(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next);
    }

    @Override
    protected final Pair<Cluster, Cluster> select() {
        return null;
    }

    @Override
    protected final Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

}
