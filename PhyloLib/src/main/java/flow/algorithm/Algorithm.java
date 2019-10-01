package flow.algorithm;

import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.algorithm.gcp.UPGMAAlgorithm;
import flow.algorithm.mst.goeburst.GoeBURSTAlgorithm;
import flow.algorithm.mst.goeburst.GoeBURSTFullMSTAlgorithm;
import flow.algorithm.mst.grapetree.GrapeTreeAlgorithm;
import flow.algorithm.nj.NeighbourJoiningAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Algorithm extends Component {

	private static final String NAME = "-algorithm";

	protected Algorithm(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract PhylogeneticTree process(DistanceMatrix matrix);

	public static Algorithm get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, new ArrayList<>(){{ add("goeburst"); }}, new HashMap<>() {{
			put("goeburst", GoeBURSTAlgorithm::new);
			put("fullmst", GoeBURSTFullMSTAlgorithm::new);
			put("nj", NeighbourJoiningAlgorithm::new);
			put("grapetree", GrapeTreeAlgorithm::new);
			put("upgma", UPGMAAlgorithm::new);
		}});
	}

}
