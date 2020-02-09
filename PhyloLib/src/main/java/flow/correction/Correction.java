package flow.correction;

import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import flow.Component;
import flow.Parameters;

import java.util.HashMap;

public abstract class Correction extends Component<Matrix> {

    public Correction(Context context, HashMap<String, String> values) throws Exception {
        super(context, context::setMatrix, IMatrixFormatter::get, values, false, true, false);
    }

    public static void run(Parameters parameters, Context context) throws Exception {
        Component.runSingle(parameters, context, "correction", new HashMap<>() {{
            put("jukescantor", JukesCantor::new);
            put("kimura", Kimura::new);
        }});
    }

}
