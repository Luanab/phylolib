package flow.algorithm;

import data.Context;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import data.tree.Cluster;
import data.tree.ITreeFormatter;
import data.tree.Tree;
import flow.Component;
import flow.Pair;
import flow.Parameters;
import flow.algorithm.gcp.UPGMA;
import flow.algorithm.gcp.WPGMA;
import flow.algorithm.mst.GoeBURST;
import flow.algorithm.mst.GrapeTree;
import flow.algorithm.nj.*;
import flow.correction.Correction;
import flow.distance.Distance;
import flow.optimization.Optimization;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class Algorithm extends Component<Matrix, Tree> {

    public static final String NAME = "algorithm";

    public Algorithm(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next, Context::setMatrix, Context::setTree, IMatrixFormatter::get, ITreeFormatter::get);
    }

    public static Optional<Algorithm> get(Parameters parameters) throws Exception {
        return Component.getSingle(parameters, NAME, new String[]{Distance.NAME, Correction.NAME}, new String[]{Optimization.NAME}, new HashMap<>() {{
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
    protected final Tree process(Context context) {
        return null;
    }

}
