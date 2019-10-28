package flow.process.mst;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class GrapeTreeAlgorithm extends MinimumSpanningTreeAlgorithm {

    public GrapeTreeAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

    @Override
    protected Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
        return null;
    }

    @Override
    protected Pair<Double, Double> join(DistanceMatrix matrix, Pair<Cluster, Cluster> clusters) {
        return null;
    }

}
