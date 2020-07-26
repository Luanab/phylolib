package benchmark;

import command.algorithm.Algorithm;
import command.algorithm.edmonds.Edmonds;
import command.algorithm.gcp.*;
import command.algorithm.goeburst.GoeBURST;
import command.algorithm.nj.SaitouNei;
import command.algorithm.nj.StudierKeppler;
import command.algorithm.nj.UNJ;
import command.distance.Hamming;
import data.matrix.Matrix;
import org.openjdk.jmh.annotations.*;

import java.lang.reflect.Field;

public class AlgorithmBenchmark {

	public static void main(String[] args) throws Exception {
		BaseBenchmark.main(AlgorithmBenchmark.class);
	}

	private static void benchmark(AlgorithmState state, Algorithm algorithm) {
		algorithm.process(state.matrix);
	}

	@Benchmark
	public void goeburst(AlgorithmState state) throws NoSuchFieldException, IllegalAccessException {
		Field lvs = GoeBURST.class.getDeclaredField("lvs");
		lvs.setAccessible(true);
		GoeBURST goeBURST = new GoeBURST();
		lvs.set(goeBURST, 3);
		benchmark(state, goeBURST);
	}

	@Benchmark
	public void edmonds(AlgorithmState state) {
		benchmark(state, new Edmonds());
	}

	@Benchmark
	public void cl(AlgorithmState state) {
		benchmark(state, new CL());
	}

	@Benchmark
	public void sl(AlgorithmState state) {
		benchmark(state, new SL());
	}

	@Benchmark
	public void upgma(AlgorithmState state) {
		benchmark(state, new UPGMA());
	}

	@Benchmark
	public void upgmc(AlgorithmState state) {
		benchmark(state, new UPGMC());
	}

	@Benchmark
	public void wpgma(AlgorithmState state) {
		benchmark(state, new WPGMA());
	}

	@Benchmark
	public void wpgmc(AlgorithmState state) {
		benchmark(state, new WPGMC());
	}

	@Benchmark
	public void saitounei(AlgorithmState state) {
		benchmark(state, new SaitouNei());
	}

	@Benchmark
	public void studierkeppler(AlgorithmState state) {
		benchmark(state, new StudierKeppler());
	}

	@Benchmark
	public void unj(AlgorithmState state) {
		benchmark(state, new UNJ());
	}

	@State(Scope.Benchmark)
	public static class AlgorithmState extends BaseBenchmark.BaseState {

		public Matrix matrix;

		@Override
		@Setup(Level.Invocation)
		public void setup() {
			super.setup();
			this.matrix = new Hamming().process(this.dataset);
		}

	}

}
