package flow.distance.implicit;

import cli.Parameters;
import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import flow.distance.Distance;

public abstract class Implicit extends Distance {

    protected Implicit(Context context, Parameters parameters) {
        super(context, parameters);
    }

    protected abstract double distance(Profile a, Profile b);

    @Override
    protected final Matrix process() {
        Dataset dataset = context.getDataset();
        int size = dataset.size();
        Double[][] matrix = new Double[size][size];
        return new Matrix(size, (i, j) -> matrix[i][j] != null ? matrix[i][j] : (matrix[i][j] = distance(dataset.get(i), dataset.get(j))));
    }

}
