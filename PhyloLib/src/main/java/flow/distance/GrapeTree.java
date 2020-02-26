package flow.distance;

import cli.Options;
import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

import java.util.Objects;

public final class GrapeTree extends Distance {

	protected GrapeTree(Context context, Options options) {
		super(context, options);
	}

	protected double distance(Profile a, Profile b) {
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

	@Override
	protected final Matrix process() {
		Dataset dataset = context.getDataset();
		int size = dataset.size();
		double[][] matrix = new double[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				matrix[i][j] = distance(dataset.get(i), dataset.get(j));
		return new Matrix(size, (i, j) -> matrix[i][j]);
	}

}
