package data;

public class DistanceMatrix {

	public final double[][] matrix;

	public DistanceMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public final int size() {
		return matrix.length;
	}

	public double get(int i, int j) {
		return matrix[i][j];
	}

}
