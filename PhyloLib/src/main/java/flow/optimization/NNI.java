package flow.optimization;

import data.Context;
import data.tree.Edge;

import java.util.HashMap;

public final class NNI extends Optimization {

    NNI(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }

    @Override
    protected Edge select() {
        return null;
    }

    @Override
    protected Edge join() {
        return null;
    }

    @Override
    protected void reduce(Edge edge) {

    }

}
