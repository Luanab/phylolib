package flow.algorithm.mst;

import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

import java.util.HashMap;

public final class GoeBURST extends MinimumSpanningTree {

    private static final String LVS = "--lvs";

    private final int lvs;

    public GoeBURST(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
        this.lvs = Integer.parseInt(values.get(LVS));
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
