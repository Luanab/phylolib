package flow;

import data.DataSet;
import data.PhylogeneticTree;
import process.algorithm.IAlgorithm;
import process.algorithm.gcp.UPGMAAlgorithm;
import process.algorithm.goeburst.GoeBURSTAlgorithm;
import process.algorithm.grapetree.GrapeTreeAlgorithm;
import process.algorithm.nj.NeighbourJoiningAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

class Algorithm extends Component<IAlgorithm, Function<DataSet, PhylogeneticTree>> {

	Algorithm() {
		this.name = "-algorithm";
		this.number = 0;
		this.options = new HashMap<>() {{
			put("goeburst", new GoeBURSTAlgorithm());
			put("nj", new NeighbourJoiningAlgorithm());
			put("grapetree", new GrapeTreeAlgorithm());
			put("upgma", new UPGMAAlgorithm());
		}};
		this.mapper = (values, algorithm) -> algorithm::process;
	}

	@Override
	List<String> defaultValues() {
		return new ArrayList<>(){{
			add("goeburst");
		}};
	}

}
