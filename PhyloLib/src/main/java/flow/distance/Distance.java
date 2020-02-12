package flow.distance;

import cli.Arguments;
import cli.Parameters;
import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import exception.ArgumentException;
import flow.Component;
import flow.distance.explicit.GrapeTree;
import flow.distance.implicit.Hamming;

import java.io.IOException;
import java.util.HashMap;

public abstract class Distance extends Component<Matrix> {

    protected Distance(Context context, Parameters parameters) {
        super(context, context::setMatrix, IMatrixFormatter::get, parameters);
        this.input |= DATASET;
    }

    public static void run(Arguments arguments, Context context) throws ArgumentException, IOException {
        Component.run(arguments, context, "distance", true, new HashMap<>() {{
            put("hamming", Hamming::new);
            put("grapetree", GrapeTree::new);
        }});
    }

}
