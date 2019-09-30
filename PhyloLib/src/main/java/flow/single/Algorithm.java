package flow.single;

import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.ParameterException;
import process.algorithm.IAlgorithm;
import process.algorithm.gcp.UPGMAAlgorithm;
import process.algorithm.mst.goeburst.GoeBURSTAlgorithm;
import process.algorithm.mst.goeburst.GoeBURSTFullMSTAlgorithm;
import process.algorithm.mst.grapetree.GrapeTreeAlgorithm;
import process.algorithm.nj.NeighbourJoiningAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Algorithm extends SingleComponent<IAlgorithm> {

	public Algorithm(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-algorithm", 0, new ArrayList<>(){{ add("goeburst"); }}, new HashMap<>() {{
			put("goeburst", new GoeBURSTAlgorithm());
			put("fullmst", new GoeBURSTFullMSTAlgorithm());
			put("nj", new NeighbourJoiningAlgorithm());
			put("grapetree", new GrapeTreeAlgorithm());
			put("upgma", new UPGMAAlgorithm());
		}});
	}

	public PhylogeneticTree process(DistanceMatrix matrix) {
		return option.process(matrix);
	}

}
