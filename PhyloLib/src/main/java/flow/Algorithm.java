package flow;

import data.DataSet;
import data.PhylogeneticTree;
import exception.ParameterException;
import process.algorithm.IAlgorithm;
import process.algorithm.clusters.gcp.UPGMAAlgorithm;
import process.algorithm.clusters.nj.NeighbourJoiningAlgorithm;
import process.algorithm.mst.goeburst.GoeBURSTAlgorithm;
import process.algorithm.mst.grapetree.GrapeTreeAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Algorithm extends Component<IAlgorithm> {

	Algorithm(HashMap<String, List<String>> parameters) throws ParameterException {
		super(parameters, "-algorithm", 0, new ArrayList<>(){{ add("goeburst"); }}, new HashMap<>() {{
			put("goeburst", new GoeBURSTAlgorithm());
			put("nj", new NeighbourJoiningAlgorithm());
			put("grapetree", new GrapeTreeAlgorithm());
			put("upgma", new UPGMAAlgorithm());
		}});
	}

	PhylogeneticTree process(DataSet dataset) {
		return option.process(dataset);
	}

}
