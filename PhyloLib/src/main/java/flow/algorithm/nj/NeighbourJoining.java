package flow.algorithm.nj;

import data.tree.Cluster;
import flow.Pair;
import flow.algorithm.Algorithm;

import java.util.List;

public abstract class NeighbourJoining extends Algorithm {

    NeighbourJoining(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next);
    }

    @Override
    protected Pair<Cluster, Cluster> select() {
        return null;
    }

    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
