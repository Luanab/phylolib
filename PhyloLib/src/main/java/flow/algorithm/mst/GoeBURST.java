package flow.algorithm.mst;

import cli.Format;
import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import exception.InvalidFormatException;

public final class GoeBURST extends MinimumSpanningTree {

	private final int lvs;

	public GoeBURST(Context context, Options options) throws InvalidFormatException {
		super(context, options);
		inputs.add(context::readDataset);
		this.lvs = Integer.parseInt(options.remove("lvs", 'l', Format.NATURAL, "3"));
	}

	@Override
	protected Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	protected Pair<Double, Double> join(Pair<Cluster, Cluster> clusters) {
		return null;
	}

}
