package flow.process;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;
import flow.process.gcp.UPGMA;
import flow.process.gcp.WPGMA;
import flow.process.mst.GoeBURST;
import flow.process.mst.GrapeTree;
import flow.process.nj.*;

import java.util.HashMap;
import java.util.List;

public abstract class Algorithm extends Component {

    protected DistanceMatrix matrix;

    protected Algorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
    }

    protected abstract Pair<Cluster, Cluster> select();

    protected abstract Pair<Double, Double> join(Pair<Cluster, Cluster> clusters);

    protected abstract void reduce(Pair<Double, Double> distances);

    public final PhylogeneticTree process(DistanceMatrix matrix) {
        this.matrix = matrix;
        return null;
    }

    public static Algorithm get(Parameters parameters) throws ParameterException {
        return parameters.map("-algorithm", "-a", "goeburst", new HashMap<>() {{
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

}
