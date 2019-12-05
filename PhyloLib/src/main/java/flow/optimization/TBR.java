package flow.optimization;

import data.Context;
import data.tree.Tree;

import java.util.List;

public class TBR extends Optimization {

    TBR(List<String> values, boolean previous, boolean next) throws Exception {
        super(values, 0, previous, next);
    }

    @Override
    protected Tree process(Context context) {
        return null;
    }

}
