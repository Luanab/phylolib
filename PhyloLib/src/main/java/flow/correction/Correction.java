package flow.correction;

import cli.Commands;
import cli.Options;
import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import flow.Component;

import java.util.HashMap;

public abstract class Correction extends Component<Matrix> {

    public Correction(Context context, Options options) throws Exception {
        super(context, context::setMatrix, IMatrixFormatter::get, options, false, true, false);
    }

    public static void run(Commands commands, Context context) throws Exception {
        Component.runSingle(commands, context, "correction", new HashMap<>() {{
            put("jukescantor", JukesCantor::new);
            put("kimura", Kimura::new);
        }});
    }

}
