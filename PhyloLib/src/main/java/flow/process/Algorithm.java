package flow.process;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import data.PhylogeneticTree;
import exception.ParameterException;
import flow.Option;
import flow.Parameters;
import flow.process.gcp.UPGMAAlgorithm;
import flow.process.mst.GoeBURSTAlgorithm;
import flow.process.mst.GrapeTreeAlgorithm;
import flow.process.nj.SaitouNeiNeighbourJoiningJAlgorithm;
import flow.process.nj.StudierKeplerNeighbourJoiningJAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Algorithm {

	protected DistanceMatrix matrix;

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
			put("goeburst", new Option<>(1, values -> new GoeBURSTAlgorithm(Integer.parseInt(values.get(0)))));
			put("snnj", new Option<>(0, values -> new SaitouNeiNeighbourJoiningJAlgorithm()));
			put("sknj", new Option<>(0, values -> new StudierKeplerNeighbourJoiningJAlgorithm()));
			put("grapetree", new Option<>(0, values -> new GrapeTreeAlgorithm()));
			put("upgma", new Option<>(0, values -> new UPGMAAlgorithm()));
		}});
	}

}
