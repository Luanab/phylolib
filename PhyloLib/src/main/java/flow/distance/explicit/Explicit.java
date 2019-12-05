package flow.distance.explicit;

import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import flow.distance.Distance;

import java.util.List;

public abstract class Explicit extends Distance {

    Explicit(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next);
    }

    protected abstract double distance(Profile a, Profile b);

    @Override
    protected Matrix process(Context context) {
        Dataset dataset = context.getDataset();
        int size = dataset.size();
        double[][] matrix = new double[size][size];
        // omp parallel for
        for (int i = 0; i < size; i++) {
            // omp parallel for
            for (int j = 0; j < size; j++) {
                matrix[i][j] = distance(dataset.get(i), dataset.get(j));
            }
        }
        return new Matrix(size, (i, j) -> matrix[i][j]);
    }

}
