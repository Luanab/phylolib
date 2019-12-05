package flow.algorithm.nj;

import data.tree.Cluster;
import flow.Pair;

import java.util.List;

public final class FNJ extends NeighbourJoining {

    public FNJ(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

    @Override
    protected Pair<Cluster, Cluster> select() {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
