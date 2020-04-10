package command.algorithm.mst;

import cli.Option;
import cli.Options;
import data.Context;
import exception.MissingInputException;

import java.util.stream.IntStream;

public final class GrapeTree extends MinimumSpanningTree {

	private int root;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		super.init(context, options);
		this.root = Integer.parseInt(options.remove(Option.ROOT, "0"));
	}

	@Override
	protected int tiebreak(int ifrom, int ito, int jfrom, int jto) {
		return (int) (Math.min(mean(ifrom), mean(ito)) - Math.min(mean(jfrom), mean(jto)));
	}

	private double mean(int i) {
		double sum = IntStream.range(0, elements())
				.filter(j -> j != i)
				.mapToDouble(j -> Math.pow(distance(i, j), -1))
				.sum();
		return Math.pow(sum / (elements() - 1), -1);
	}

}
