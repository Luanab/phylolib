package command.algorithm.mst;

import java.util.stream.IntStream;

public final class GrapeTree extends MinimumSpanningTree {

	@Override
	protected int tiebreak(int ifrom, int ito, int jfrom, int jto) {
		return Double.compare(Math.min(mean(ifrom), mean(ito)), Math.min(mean(jfrom), mean(jto)));
	}

	private double mean(int i) {
		return (elements() - 1) / IntStream.range(0, elements()).filter(j -> j != i).mapToDouble(j -> 1 / distance(i, j)).sum();
	}

}
