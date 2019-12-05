package flow.correction;

import data.Context;
import data.matrix.Matrix;

import java.util.List;

public class JukesCantor extends Correction {

    public JukesCantor(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

    @Override
    protected Matrix process(Context context) {
        return null;
    }

}
