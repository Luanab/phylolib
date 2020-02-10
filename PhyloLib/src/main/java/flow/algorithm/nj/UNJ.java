package flow.algorithm.nj;

import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class UNJ extends NeighbourJoining {

    public UNJ(Context context, Options options) throws Exception {
        super(context, options);
    }


    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
