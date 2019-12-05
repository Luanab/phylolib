package flow.algorithm.nj;

import java.util.List;

public final class StudierKepler extends NeighbourJoining {

    public StudierKepler(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

}
