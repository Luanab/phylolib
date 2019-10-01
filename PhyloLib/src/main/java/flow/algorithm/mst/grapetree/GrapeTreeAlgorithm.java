package flow.algorithm.mst.grapetree;

import data.*;
import exception.NumberOfArgumentsException;
import flow.algorithm.mst.MinimumSpanningTreeAlgorithm;

import java.util.List;

public final class GrapeTreeAlgorithm extends MinimumSpanningTreeAlgorithm {

	public GrapeTreeAlgorithm(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

}
