package flow.distance.explicit;

import cli.Parameters;
import data.Context;
import data.dataset.Profile;

public final class GrapeTree extends Explicit {

    public GrapeTree(Context context, Parameters parameters) {
        super(context, parameters);
    }

    @Override
    protected double distance(Profile a, Profile b) {
        return 0;
    }

}
