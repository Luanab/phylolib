package flow.algorithm;

import cli.Commands;
import cli.Parameters;
import data.Context;
import data.tree.Cluster;
import data.tree.ITreeFormatter;
import data.tree.Pair;
import data.tree.Tree;
import exception.InvalidFormatException;
import exception.InvalidTypeException;
import exception.MissingOptionException;
import exception.RepeatedCommandException;
import flow.Component;
import flow.algorithm.gcp.UPGMA;
import flow.algorithm.gcp.WPGMA;
import flow.algorithm.mst.GoeBURST;
import flow.algorithm.mst.GrapeTree;
import flow.algorithm.nj.*;

import java.io.IOException;
import java.util.HashMap;

public abstract class Algorithm extends Component<Tree> {

    protected Algorithm(Context context, Parameters parameters) {
        super(context, context::setTree, ITreeFormatter::get, parameters, false, true, false);
    }

    protected Algorithm(Context context, Parameters parameters, boolean dataset, boolean matrix, boolean tree) {
        super(context, context::setTree, ITreeFormatter::get, parameters, dataset, matrix, tree);
    }

    public static void run(Commands commands, Context context) throws InvalidTypeException, RepeatedCommandException, IOException, MissingOptionException, InvalidFormatException {
        Component.runSingle(commands, context, "algorithm", new HashMap<>() {{
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
