package flow.algorithm.nj;

import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

import java.util.HashMap;

public final class UNJ extends NeighbourJoining {

    public UNJ(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }


    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
