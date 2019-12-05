package flow.distance;

import data.Context;
import data.dataset.Dataset;
import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import flow.Component;
import flow.Parameters;
import flow.algorithm.Algorithm;
import flow.correction.Correction;
import flow.distance.explicit.GrapeTree;
import flow.distance.implicit.Hamming;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Distance extends Component<Dataset, Matrix> {

    public static final String NAME = "distance";

    public Distance(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next, Context::setDataset, Context::setMatrix, IDatasetFormatter::get, IMatrixFormatter::get);
    }

    public static Optional<Distance> get(Parameters parameters) throws Exception {
        return Component.getSingle(parameters, NAME, new String[0], new String[]{Correction.NAME, Algorithm.NAME}, new HashMap<>() {{
            put("hamming", Hamming::new);
            put("grapetree", GrapeTree::new);
        }});
    }

}
