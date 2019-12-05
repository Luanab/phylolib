package flow.distance.implicit;

import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import flow.distance.Distance;

import java.util.List;

public abstract class Implicit extends Distance {

    Implicit(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next);
    }

    abstract double distance(Profile a, Profile b);

    @Override
    protected Matrix process(Context context) {
        Dataset dataset = context.getDataset();
        int size = dataset.size();
        Double[][] matrix = new Double[size][size];
        return new Matrix(size, (i, j) -> matrix[i][j] != null ? matrix[i][j] : (matrix[i][j] = distance(dataset.get(i), dataset.get(j))));
    }

}
