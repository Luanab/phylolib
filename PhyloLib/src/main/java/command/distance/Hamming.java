package command.distance;

import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

import java.util.Objects;

public final class Hamming extends Distance {

	@Override
	public final Matrix process(Dataset dataset) {
		int size = dataset.size() - 1;
		Double[][] matrix = new Double[size][];
		for (int i = 0; i < size; i++)
			matrix[i] = new Double[size - i];
		return new Matrix(size + 1, (i, j) -> provide(dataset, matrix, Math.min(i, j), Math.max(i, j)));
	}

	private Double provide(Dataset dataset, Double[][] matrix, int i, int j) {
		return matrix[i][j] != null ?
			   matrix[i][j] :
			   (matrix[i][j] = distance(dataset.get(i), dataset.get(j)));
	}

	private double distance(Profile a, Profile b) {
		double distance = 0;
		for (int i = 0; i < a.length(); i++)
			if (!Objects.equals(a.getLocus(i), b.getLocus(i)))
				distance++;
		return distance;
	}

}
