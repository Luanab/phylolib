package flow.process.nj;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UNJ extends NeighbourJoining {

    public UNJ(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

    @Override
    protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
