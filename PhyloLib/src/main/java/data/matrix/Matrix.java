package data.matrix;

import java.util.List;

public final class Matrix {

	private final Double[][] values;
	private final IDistance distance;

	public Matrix(int size, IDistance distance) {
		this.values = new Double[size][size];
		this.distance = distance;
	}

	public Matrix(List<List<Double>> values) {
		this.values = values.stream().map(l -> l.toArray(new Double[0])).toArray(Double[][]::new);
		this.distance = null;
	}

	public final int size() {
		return values.length;
	}

	public double get(int i, int j) {
		return values[i][j] != null ? values[i][j] : (values[i][j] = distance.get(i, j));
	}

}
