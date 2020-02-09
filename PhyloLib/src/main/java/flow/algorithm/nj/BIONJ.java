package flow.algorithm.nj;

import data.Context;
import data.tree.Cluster;
import data.tree.Pair;

import java.util.HashMap;

public final class BIONJ extends NeighbourJoining {

    public BIONJ(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }


    @Override
    protected Pair<Cluster, Cluster> select() {
        return null;
    }

    @Override
    protected void reduce(Pair<Double, Double> distances) {

    }

}
