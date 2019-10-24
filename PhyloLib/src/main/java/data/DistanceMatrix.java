package data;

public class DistanceMatrix {

	public interface DistanceGetter {
		double get(int i, int j);
	}

	private final int size;
	private final DistanceGetter provider;

	public DistanceMatrix(int size, DistanceGetter provider) {
		this.size = size;
		this.provider = provider;
	}

	public final int size() {
		return size;
	}

	public double get(int i, int j) {
		return provider.get(i, j);
	}

}
