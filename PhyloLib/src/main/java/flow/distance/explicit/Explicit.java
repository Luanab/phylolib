package flow.distance.explicit;

import cli.Options;
import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import flow.distance.Distance;

public abstract class Explicit extends Distance {

    Explicit(Context context, Options options) throws Exception {
        super(context, options);
    }

    protected abstract double distance(Profile a, Profile b);

    @Override
    protected final Matrix process() {
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
