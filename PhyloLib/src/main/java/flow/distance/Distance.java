package flow.distance;

import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import flow.Component;
import flow.Parameters;
import flow.distance.explicit.GrapeTree;
import flow.distance.implicit.Hamming;

import java.util.HashMap;

public abstract class Distance extends Component<Matrix> {

    public Distance(Context context, HashMap<String, String> values) throws Exception {
        super(context, context::setMatrix, IMatrixFormatter::get, values, true, false, false);
    }

    public static void run(Parameters parameters, Context context) throws Exception {
        Component.runSingle(parameters, context, "distance", new HashMap<>() {{
            put("hamming", Hamming::new);
            put("grapetree", GrapeTree::new);
        }});
    }

}
