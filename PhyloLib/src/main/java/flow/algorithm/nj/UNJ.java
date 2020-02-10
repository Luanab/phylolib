package flow.algorithm.nj;

import cli.Parameters;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class UNJ extends NeighbourJoining {

    public UNJ(Context context, Parameters parameters) {
        super(context, parameters);
    }

    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
