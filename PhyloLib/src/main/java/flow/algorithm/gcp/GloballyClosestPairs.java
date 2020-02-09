package flow.algorithm.gcp;

import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import flow.algorithm.Algorithm;

import java.util.HashMap;

public abstract class GloballyClosestPairs extends Algorithm {

    GloballyClosestPairs(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
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
