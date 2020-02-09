package flow.distance.explicit;

import data.Context;
import data.dataset.Profile;

import java.util.HashMap;

public final class GrapeTree extends Explicit {

    public GrapeTree(Context context, HashMap<String, String> values) throws Exception {
        super(context, values);
    }

    @Override
    protected double distance(Profile a, Profile b) {
        return 0;
    }

}
