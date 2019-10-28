package flow.process.mst;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class GoeBURST extends MinimumSpanningTree {

    private static final int DEFAULT_LVS = 3;

    private final int lvs;

    public GoeBURST(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
        this.lvs = parameters.isEmpty() ? DEFAULT_LVS : Integer.parseInt(parameters.remove(0));
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
