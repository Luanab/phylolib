package flow.process.mst;

import data.Pair;
import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class MinimumSpanningTree extends Algorithm {

    MinimumSpanningTree(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
    }

    @Override
    protected final void reduce(Pair<Double, Double> distances) {
        // ignore this step
    }

}
