package flow.correction;

import cli.Parameters;
import data.Context;
import data.matrix.Matrix;

public final class Kimura extends Correction {

    public Kimura(Context context, Parameters parameters) {
        super(context, parameters);
        this.input |= DATASET;
    }

    @Override
    protected Matrix process() {
        return null;
    }

}
