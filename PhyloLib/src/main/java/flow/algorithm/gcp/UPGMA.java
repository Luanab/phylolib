package flow.algorithm.gcp;


import flow.Pair;

import java.util.List;

public final class UPGMA extends GloballyClosestPairs {

    public UPGMA(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
