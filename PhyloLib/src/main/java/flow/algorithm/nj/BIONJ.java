package flow.algorithm.nj;

import data.tree.Cluster;
import flow.Pair;

import java.util.List;

public final class BIONJ extends NeighbourJoining {

    public BIONJ(List<String> values, boolean previous, boolean next) throws Exception {
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
