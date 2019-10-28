package flow.process.gcp;

import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UPGMA extends GloballyClosestPairs {

    public UPGMA(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
