package flow.correction;

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

import java.io.IOException;
import java.util.HashMap;

public abstract class Correction extends Component<Matrix> {

    protected Correction(Context context, Parameters parameters) {
        super(context, context::setMatrix, IMatrixFormatter::get, parameters, false, true, false);
    }

    public static void run(Commands commands, Context context) throws InvalidTypeException, RepeatedCommandException, IOException, MissingOptionException, InvalidFormatException {
        Component.runSingle(commands, context, "correction", new HashMap<>() {{
            put("jukescantor", JukesCantor::new);
            put("kimura", Kimura::new);
        }});
    }

}
