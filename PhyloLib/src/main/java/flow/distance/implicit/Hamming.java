package flow.distance.implicit;

import cli.Parameters;
import data.Context;
import data.dataset.Profile;

public final class Hamming extends Implicit {

    public Hamming(Context context, Parameters parameters) {
        super(context, parameters);
    }

    @Override
    protected double distance(Profile a, Profile b) {
        return 0;
    }

}
