package flow.distance.explicit;

import cli.Options;
import data.Context;
import data.dataset.Profile;

public final class GrapeTree extends Explicit {

    public GrapeTree(Context context, Options options) throws Exception {
        super(context, options);
    }

    @Override
    protected double distance(Profile a, Profile b) {
        return 0;
    }

}
