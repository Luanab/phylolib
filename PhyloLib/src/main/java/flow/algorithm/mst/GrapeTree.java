package flow.algorithm.mst;

import data.tree.Cluster;
import flow.Pair;

import java.util.List;

public final class GrapeTree extends MinimumSpanningTree {

    public GrapeTree(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
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
