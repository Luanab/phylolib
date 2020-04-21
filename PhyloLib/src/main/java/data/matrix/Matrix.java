package data.matrix;

public final class Matrix {

	private final String[] ids;
	private final Double[][] values;
	private final IDistance distance;

	public Matrix(String[] ids, IDistance distance) {
		this.ids = ids;
		this.values = new Double[ids.length][ids.length];
		this.distance = distance;
	}

	public Matrix(String[] ids, Double[][] values) {
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
		return values[i][j] != null ? values[i][j] : (values[i][j] = i == j ? 0 : distance.get(i, j));
	}

}
