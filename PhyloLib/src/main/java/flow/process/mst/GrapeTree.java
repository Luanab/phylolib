package flow.process.mst;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class GrapeTree extends MinimumSpanningTree {

    public GrapeTree(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
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
