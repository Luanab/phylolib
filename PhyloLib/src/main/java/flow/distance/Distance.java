package flow.distance;

import cli.Commands;
import cli.Parameters;
import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import exception.InvalidFormatException;
import exception.InvalidTypeException;
import exception.MissingOptionException;
import exception.RepeatedCommandException;
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

    public static void run(Commands commands, Context context) throws InvalidTypeException, RepeatedCommandException, IOException, MissingOptionException, InvalidFormatException {
        Component.runSingle(commands, context, "distance", new HashMap<>() {{
            put("hamming", Hamming::new);
            put("grapetree", GrapeTree::new);
        }});
    }

}
