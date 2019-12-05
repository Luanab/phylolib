package flow.algorithm.nj;

import data.tree.Cluster;
import flow.Pair;

import java.util.List;

public final class UNJ extends NeighbourJoining {

    public UNJ(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
