package flow.correction;

import data.Context;
import data.matrix.Matrix;

import java.util.HashMap;

public final class JukesCantor extends Correction {

    public JukesCantor(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }

    @Override
    protected Matrix process() {
        return null;
    }

}
