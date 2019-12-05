package flow.correction;

import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import flow.Component;
import flow.Parameters;
import flow.algorithm.Algorithm;
import flow.distance.Distance;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Correction extends Component<Matrix, Matrix> {

    public static final String NAME = "correction";

    public Correction(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next, Context::setMatrix, Context::setMatrix, IMatrixFormatter::get, IMatrixFormatter::get);
    }

    public static Optional<Correction> get(Parameters parameters) throws Exception {
        return Component.getSingle(parameters, NAME, new String[]{Distance.NAME}, new String[]{Algorithm.NAME}, new HashMap<>() {{
            put("jukescantor", JukesCantor::new);
        }});
    }

}
