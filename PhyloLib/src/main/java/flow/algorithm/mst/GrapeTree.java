package flow.algorithm.mst;

import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

import java.util.HashMap;

public final class GrapeTree extends MinimumSpanningTree {

    public GrapeTree(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }


    @Override
    protected Pair<Cluster, Cluster> select() {
        return null;
    }

    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

}
