package flow.distance;

import cli.Parameters;
import data.Context;
import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;

import java.util.Objects;

public final class Kimura extends Distance {

	protected Kimura(Context context, Parameters parameters) {
		super(context, parameters);
	}

	private double distance(Profile a, Profile b) {
		int transitions = 0;
		int transversions = 0;
		for (int i = 0; i < a.length(); i++) {
			Integer first = a.getLocus(i);
			Integer second = b.getLocus(i);
			if (Objects.equals(first, second) || first == null || second == null)
				continue;
			int min = Math.min(first, second);
			int max = Math.max(first, second);
			if (min == 'A' && max == 'G' || min == 'C' && max == 'T')
				transitions++;
			else
				transversions++;
		}
		return -Math.log((1 - 2 * transitions - transversions) * Math.sqrt(1 - 2 * transversions)) / 2;
	}

	@Override
	protected final Matrix process() {
		Dataset dataset = context.getDataset();
		int size = dataset.size() - 1;
		Double[][] matrix = new Double[size][];
		for (int i = 0; i < size; i++) {
			matrix[i] = new Double[size - i];
			for (int j = 0; j < size - i; j++)
				matrix[i][j] = distance(dataset.get(i), dataset.get(j + 1 + i));
		}
		return new Matrix(size + 1, (i, j) -> matrix[Math.min(i, j)][Math.max(i, j)]);
	}

}
