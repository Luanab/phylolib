package flow.algorithm;

import data.Context;
import data.tree.Cluster;
import data.tree.ITreeFormatter;
import data.tree.Pair;
import data.tree.Tree;
import flow.Component;
import flow.Parameters;
import flow.algorithm.gcp.UPGMA;
import flow.algorithm.gcp.WPGMA;
import flow.algorithm.mst.GoeBURST;
import flow.algorithm.mst.GrapeTree;
import flow.algorithm.nj.*;

import java.util.HashMap;

public abstract class Algorithm extends Component<Tree> {

    public Algorithm(Context context, HashMap<String, String> values) throws Exception {
        super(context, context::setTree, ITreeFormatter::get, values, false, true, false);
    }

    public static void run(Parameters parameters, Context context) throws Exception {
        Component.runSingle(parameters, context, "algorithm", new HashMap<>() {{
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
