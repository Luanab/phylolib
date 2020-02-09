package flow.algorithm.nj;

import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import flow.algorithm.Algorithm;

import java.util.HashMap;

public abstract class NeighbourJoining extends Algorithm {

    NeighbourJoining(Context context, HashMap<String, String> values) throws Exception {
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

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
