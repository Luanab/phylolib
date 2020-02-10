package flow.algorithm.mst;

import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class GoeBURST extends MinimumSpanningTree {

    private final int lvs;

    public GoeBURST(Context context, Options options) throws Exception {
        super(context, options);
        this.lvs = Integer.parseInt(options.getOrDefault("lvs", "3"));
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
