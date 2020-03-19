package data.tree;

import data.matrix.Matrix;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class ClusterSet {

	private final int elements;
	private final HashMap<Integer, HashMap<Integer, Supplier<Double>>> clusters;

	public ClusterSet(Matrix matrix) {
		this.elements = matrix.size();
		this.clusters = new HashMap<>() {{
			for (int i = 0; i < elements; i++) {
				int first = i;
				put(i, new HashMap<>() {{
					for (int j = 0; j < elements; j++) {
						int second = j;
						put(j, () -> matrix.get(first, second));
					}
				}});
			}
		}};
	}

	public Stream<Integer> elements() {
		return clusters.keySet().stream().filter(i -> i < elements);
	}

	public Stream<Integer> clusters() {
		return clusters.keySet().stream().filter(i -> clusters.get(i) != null);
	}

	public long count(int i) {
		return clusters.get(i).values().stream().filter(Objects::nonNull).count();
	}

	public double get(int i, int j) {
		return clusters.get(i).get(j).get();
	}

	public void remove(int i) {
		clusters.put(i, null);
		//clusters.values().forEach(cluster -> cluster.put(i, null));
	}

	public void put(int i, int j, double ij) {
		clusters.computeIfAbsent(i, k -> new HashMap<>()).put(j, () -> ij);
	}

	public void put(int i, Function<Integer, Double> function) {
		clusters.put(i, new HashMap<>());
		clusters.forEach((j, cluster) -> {
			Supplier<Double> distance = () -> function.apply(j);
			clusters.get(i).put(j, distance);
			clusters.get(j).put(i, distance);
		});
	}

}
