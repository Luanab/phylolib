package benchmark;

import command.algorithm.edmonds.Edmonds;
import command.distance.GrapeTree;
import command.optimization.LBR;
import command.optimization.Optimization;
import data.tree.Tree;
import org.openjdk.jmh.annotations.*;

public class OptimizationBenchmark {

	public static void main(String[] args) throws Exception {
		BaseBenchmark.main(OptimizationBenchmark.class);
	}

	private static void benchmark(OptimizationState state, Optimization optimization) {
		optimization.process(state.tree);
	}

	@Benchmark
	public void lbr(OptimizationState state) {
		benchmark(state, new LBR());
	}

	@State(Scope.Benchmark)
	public static class OptimizationState extends BaseBenchmark.BaseState {

		public Tree tree;

		@Override
		@Setup(Level.Invocation)
		public void setup() {
			super.setup();
			this.tree = new Edmonds().process(new GrapeTree().process(this.dataset));
		}

	}

}
