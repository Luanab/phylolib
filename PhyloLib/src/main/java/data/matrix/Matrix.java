package data.matrix;

public final class Matrix {

	private final int size;
	private final IDistance distance;

	public Matrix(int size, IDistance distance) {
		this.size = size;
		this.distance = distance;
	}

	public final int size() {
		return size;
	}

	public double get(int i, int j) {
		return distance.get(i, j);
	}

}
