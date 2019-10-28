package flow.calculate.profile.implicit;

import data.DataSet;
import data.DistanceMatrix;
import data.Profile;
import exception.NumberOfArgumentsException;
import flow.calculate.Calculator;

import java.util.List;

public abstract class ImplicitCalculator extends Calculator {

    ImplicitCalculator(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
    }

    abstract double distance(Profile a, Profile b);

    @Override
    public final DistanceMatrix calculate(DataSet dataset) {
        int size = dataset.size();
        Double[][] matrix = new Double[size][size];
        return new DistanceMatrix(size, (i, j) ->
                matrix[i][j] != null ? matrix[i][j] : (matrix[i][j] = distance(dataset.get(i), dataset.get(j))));
    }

}
