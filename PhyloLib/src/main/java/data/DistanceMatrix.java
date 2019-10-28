package data;

public class DistanceMatrix {

    public interface DistanceProvider {
        double provide(int i, int j);
    }

    private final int size;
    private final DistanceProvider provider;

    public DistanceMatrix(int size, DistanceProvider provider) {
        this.size = size;
        this.provider = provider;
    }

    public final int size() {
        return size;
    }

    public double get(int i, int j) {
        return provider.provide(i, j);
    }

}
