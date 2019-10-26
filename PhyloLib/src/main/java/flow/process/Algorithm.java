package flow.process;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;
import flow.process.gcp.UPGMAAlgorithm;
import flow.process.gcp.WPGMAAlgorithm;
import flow.process.mst.GoeBURSTAlgorithm;
import flow.process.mst.GrapeTreeAlgorithm;
import flow.process.nj.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Algorithm extends Component {

	protected Algorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	protected abstract Pair<Cluster, Cluster> select(DistanceMatrix matrix);
	protected abstract Pair<Double, Double> join(DistanceMatrix matrix, Pair<Cluster, Cluster> clusters);
	protected abstract void reduce(DistanceMatrix matrix, Pair<Double, Double> distances);

	public final PhylogeneticTree process(DistanceMatrix matrix) {
		return null;
	}

	public static Algorithm get(Parameters parameters) throws ParameterException {
		return parameters.map("-algorithm", "-a", new ArrayList<>(){{ add("goeburst"); }}, new HashMap<>() {{
			put("goeburst", GoeBURSTAlgorithm::new);
			put("grapetree", GrapeTreeAlgorithm::new);
			put("upgma", UPGMAAlgorithm::new);
			put("wpgma", WPGMAAlgorithm::new);
			put("saitounei", SaitouNeiAlgorithm::new);
			put("studierkepler", StudierKeplerAlgorithm::new);
			put("unj", UNJAlgorithm::new);
			put("fnj", FNJAlgorithm::new);
			put("bionj", BIONJAlgorithm::new);
		}});
	}

}
