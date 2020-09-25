package benchmark;

import command.correction.Correction;
import command.correction.JukesCantor;
import command.distance.Hamming;
import data.matrix.Matrix;
import org.openjdk.jmh.annotations.*;

public class CorrectionBenchmark {

	public static void main(String[] args) throws Exception {
		BaseBenchmark.main(CorrectionBenchmark.class);
	}

	private static void benchmark(CorrectionState state, Correction correction) {
		Matrix matrix = correction.process(state.matrix);
		for (int i = 0; i < matrix.size(); i++)
			for (int j = 0; j < matrix.size(); j++)
				matrix.distance(i, j);
	}

	@Benchmark
	public void jukescantor(CorrectionState state) {
		benchmark(state, new JukesCantor());
	}

	@State(Scope.Benchmark)
	public static class CorrectionState extends BaseBenchmark.BaseState {

		public Matrix matrix;

		@Override
		@Setup(Level.Trial)
		public void setup() {
			super.setup();
			this.matrix = new Hamming().process(this.dataset);
		}

	}

}
