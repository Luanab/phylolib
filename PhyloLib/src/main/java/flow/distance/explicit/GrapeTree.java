package flow.distance.explicit;

import data.dataset.Profile;

import java.util.List;

public final class GrapeTree extends Explicit {

    public GrapeTree(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

    @Override
    protected final double distance(Profile a, Profile b) {
        return 0;
    }

}
