package flow.algorithm.gcp;

import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import flow.algorithm.Algorithm;

public abstract class GloballyClosestPairs extends Algorithm {

    GloballyClosestPairs(Context context, Options options) throws Exception {
        super(context, options);
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
