package flow.distance;

import cli.Commands;
import cli.Options;
import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import flow.Component;
import flow.distance.explicit.GrapeTree;
import flow.distance.implicit.Hamming;

import java.util.HashMap;

public abstract class Distance extends Component<Matrix> {

    public Distance(Context context, Options options) throws Exception {
        super(context, context::setMatrix, IMatrixFormatter::get, options, true, false, false);
    }

    public static void run(Commands commands, Context context) throws Exception {
        Component.runSingle(commands, context, "distance", new HashMap<>() {{
            put("hamming", Hamming::new);
            put("grapetree", GrapeTree::new);
        }});
    }

}
