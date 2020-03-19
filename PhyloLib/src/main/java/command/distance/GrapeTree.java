package command.distance;

import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

import java.util.Objects;

public final class GrapeTree extends Distance {

	@Override
	public final Matrix process(Dataset dataset) {
		int size = dataset.size();
		double[][] matrix = new double[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				matrix[i][j] = distance(dataset.get(i), dataset.get(j));
		return new Matrix(size, (i, j) -> matrix[i][j]);
	}

	private double distance(Profile a, Profile b) {
		double distance = 0;
		double missings = 0;
		for (int i = 0; i < a.length(); i++) {
			if (b.getLocus(i) == null)
				missings++;
			if (!Objects.equals(a.getLocus(i), b.getLocus(i)) && b.getLocus(i) != null)
				distance++;
		}
		return distance / missings;
	}

}
