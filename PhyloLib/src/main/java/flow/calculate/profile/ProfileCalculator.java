package flow.calculate.profile;

import data.DataSet;
import data.DistanceMatrix;
import data.Profile;
import flow.calculate.Calculator;

public abstract class ProfileCalculator extends Calculator {

	protected abstract int distance(Profile a, Profile b);

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
		return new DistanceMatrix(matrix);
	}

}
