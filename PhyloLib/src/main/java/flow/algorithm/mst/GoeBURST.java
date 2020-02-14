package flow.algorithm.mst;

import cli.Parameters;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

public final class GoeBURST extends MinimumSpanningTree {

    private final int lvs;

    public GoeBURST(Context context, Parameters parameters) {
        super(context, parameters);
        this.input |= DATASET;
        this.lvs = parameters.options.get("lvs", 'l').map(Integer::parseInt).orElse(3);
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
