package flow.process.nj;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class NeighbourJoining extends Algorithm {

    NeighbourJoining(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
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
