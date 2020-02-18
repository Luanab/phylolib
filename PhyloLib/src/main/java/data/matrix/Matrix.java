package data.matrix;

public final class Matrix {

	private final int size;
	private final DistanceProvider provider;

	public Matrix(int size, DistanceProvider provider) {
		this.size = size;
		this.provider = provider;
	}

	public final int size() {
		return size;
	}

	public double get(int i, int j) {
		return provider.provide(i, j);
	}

	public interface DistanceProvider {

		double provide(int i, int j);

	}

}
