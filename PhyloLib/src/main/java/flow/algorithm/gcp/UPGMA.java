package flow.algorithm.gcp;


import data.Context;
import data.tree.Pair;

import java.util.HashMap;

public final class UPGMA extends GloballyClosestPairs {

    public UPGMA(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }


    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
