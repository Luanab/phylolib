package flow.distance;

import cli.Options;
import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

import java.util.Objects;

public final class Hamming extends Distance {

	protected Hamming(Context context, Options options) {
		super(context, options);
	}

	private double distance(Profile a, Profile b) {
		double distance = 0;
		for (int i = 0; i < a.length(); i++)
			if (!Objects.equals(a.getLocus(i), b.getLocus(i)))
				distance++;
		return distance;
	}

	private Double provide(Dataset dataset, Double[][] matrix, int i, int j) {
		return matrix[i][j] != null ?
			   matrix[i][j] :
			   (matrix[i][j] = distance(dataset.get(i), dataset.get(j)));
	}

	@Override
	protected final Matrix process() {
		Dataset dataset = context.getDataset();
		int size = dataset.size() - 1;
		Double[][] matrix = new Double[size][];
		for (int i = 0; i < size; i++)
			matrix[i] = new Double[size - i];
		return new Matrix(size + 1, (i, j) -> provide(dataset, matrix, Math.min(i, j), Math.max(i, j)));
	}

}
