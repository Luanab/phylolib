package flow.algorithm.mst;

import data.tree.Cluster;
import flow.Pair;

import java.util.List;

public final class GoeBURST extends MinimumSpanningTree {

    private final int lvs;

    public GoeBURST(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 1, previous, next);
        this.lvs = Integer.parseInt(values.get(0));
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
