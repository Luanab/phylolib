package flow.algorithm;

import cli.Arguments;
import cli.Options;
import data.Context;
import data.tree.Cluster;
import data.tree.Pair;
import data.tree.Tree;
import exception.ArgumentException;
import flow.Component;
import flow.algorithm.gcp.UPGMA;
import flow.algorithm.gcp.WPGMA;
import flow.algorithm.mst.GoeBURST;
import flow.algorithm.mst.GrapeTree;
import flow.algorithm.nj.*;

import java.io.IOException;
import java.util.HashMap;

public abstract class Algorithm extends Component<Tree> {

	public static final String NAME = "algorithm";

	protected Algorithm(Context context, Options options) {
		super(context, options, context::readMatrix, context::writeTree);
	}

	public static void run(Arguments arguments, Context context) throws ArgumentException, IOException {
		Component.run(arguments, context, NAME, true, new HashMap<>() {{
			put("goeburst", GoeBURST::new);
			put("grapetree", GrapeTree::new);
			put("upgma", UPGMA::new);
			put("wpgma", WPGMA::new);
			put("saitounei", SaitouNei::new);
			put("studierkepler", StudierKepler::new);
			put("unj", UNJ::new);
			put("fnj", FNJ::new);
			put("bionj", BIONJ::new);
		}});
	}

	protected abstract Pair<Cluster, Cluster> select();

	protected abstract Pair<Double, Double> join(Pair<Cluster, Cluster> clusters);

	protected abstract void reduce(Pair<Double, Double> distances);

	@Override
	protected final Tree process() {
		return null;
	}

}
