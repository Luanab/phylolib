package flow.distance.implicit;

import cli.Options;
import data.Context;
import data.dataset.Profile;

public final class Hamming extends Implicit {

    public Hamming(Context context, Options options) throws Exception {
        super(context, options);
    }

    @Override
    protected double distance(Profile a, Profile b) {
        return 0;
    }

}
