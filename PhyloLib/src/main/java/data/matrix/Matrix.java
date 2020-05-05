package data.matrix;

import java.util.Arrays;

public final class Matrix {

	private final boolean symmetric;
	private final String[] ids;
	private final Double[][] values;
	private final IDistance distance;

	public Matrix(boolean symmetric, String[] ids, IDistance distance) {
		this.symmetric = symmetric;
		this.ids = ids;
		if (symmetric) {
			this.values = new Double[ids.length][];
			for (int i = 0; i < ids.length; i++)
				this.values[i] = new Double[i];
		} else
			this.values = new Double[ids.length][ids.length];
		this.distance = distance;
	}

	public Matrix(boolean symmetric, String[] ids, Double[][] values) {
		this.symmetric = symmetric;
		this.ids = ids;
		this.values = values;
		this.distance = (i, j) -> 0;
	}

	public String[] ids() {
		return ids;
	}

	public final int size() {
		return values.length;
	}

	public double distance(int i, int j) {
		if (i == j)
			return 0;
		if (symmetric) {
			int k = i;
			i = Math.max(i, j);
			j = Math.min(k, j);
		}
		return values[i][j] != null ? values[i][j] : (values[i][j] = distance.get(i, j));
	}

	public Matrix correct(ICorrection correction) {
		return values[1][0] == null
			   ? new Matrix(symmetric, ids, (i, j) -> correction.get(distance.get(i, j)))
			   : new Matrix(symmetric, ids, Arrays.stream(values).map(line -> Arrays.stream(line).map(correction::get).toArray(Double[]::new)).toArray(Double[][]::new));
	}

	public interface IDistance {

		double get(int i, int j);

	}

	public interface ICorrection {

		double get(double distance);

	}

}
