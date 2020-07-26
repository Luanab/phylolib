package benchmark;

import command.distance.Distance;
import command.distance.GrapeTree;
import command.distance.Hamming;
import command.distance.Kimura;
import data.matrix.Matrix;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public class DistanceBenchmark {

	public static void main(String[] args) throws Exception {
		BaseBenchmark.main(DistanceBenchmark.class);
	}

	private static void benchmark(DistanceState state, Distance distance) {
		Matrix matrix = distance.process(state.dataset);
		for (int i = 0; i < matrix.size(); i++)
			for (int j = 0; j < matrix.size(); j++)
				matrix.distance(i, j);
	}

	@Benchmark
	public void grapetree(DistanceState state) {
		benchmark(state, new GrapeTree());
	}

	@Benchmark
	public void hamming(DistanceState state) {
		benchmark(state, new Hamming());
	}

	@Benchmark
	public void kimura(DistanceState state) {
		benchmark(state, new Kimura());
	}

	@State(Scope.Benchmark)
	public static class DistanceState extends BaseBenchmark.BaseState {

	}

}
