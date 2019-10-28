package flow.calculate.profile.explicit;

import data.DataSet;
import data.DistanceMatrix;
import data.Profile;
import exception.NumberOfArgumentsException;
import flow.calculate.Calculator;

import java.util.List;

public abstract class ExplicitCalculator extends Calculator {

    ExplicitCalculator(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
    }

    protected abstract double distance(Profile a, Profile b);

    @Override
    public final DistanceMatrix calculate(DataSet dataset) {
        int size = dataset.size();
        double[][] matrix = new double[size][size];
        // omp parallel for
        for (int i = 0; i < size; i++) {
            // omp parallel for
            for (int j = 0; j < size; j++) {
                matrix[i][j] = distance(dataset.get(i), dataset.get(j));
            }
        }
        return new DistanceMatrix(size, (i, j) -> matrix[i][j]);
    }

}
