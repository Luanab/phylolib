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
import flow.process.mst.GoeBURSTAlgorithm;
import flow.process.mst.GrapeTreeAlgorithm;
import flow.process.nj.SaitouNeiNeighbourJoiningJAlgorithm;
import flow.process.nj.StudierKeplerNeighbourJoiningJAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Algorithm extends Component {

	protected DistanceMatrix matrix;

	protected Algorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	protected abstract void init();
	protected abstract Pair<Cluster, Cluster> select();
	protected abstract void join();
	protected abstract void reduce();

	public final PhylogeneticTree process(DistanceMatrix matrix) {
		this.matrix = matrix;
		return null;
	}

	public static Algorithm get(Parameters parameters) throws ParameterException {
		return parameters.map("-algorithm", "-a", new ArrayList<>(){{ add("goeburst"); add("3"); }}, new HashMap<>() {{
			put("goeburst", GoeBURSTAlgorithm::new);
			put("snnj", SaitouNeiNeighbourJoiningJAlgorithm::new);
			put("sknj", StudierKeplerNeighbourJoiningJAlgorithm::new);
			put("grapetree", GrapeTreeAlgorithm::new);
			put("upgma", UPGMAAlgorithm::new);
		}});
	}

}
