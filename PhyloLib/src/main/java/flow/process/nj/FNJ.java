package flow.process.nj;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class FNJ extends NeighbourJoining {

    public FNJ(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

    @Override
    protected Pair<Cluster, Cluster> select() {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
