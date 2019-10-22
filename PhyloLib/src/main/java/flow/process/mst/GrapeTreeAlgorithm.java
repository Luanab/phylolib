package flow.process.mst;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class GrapeTreeAlgorithm extends MinimumSpanningTreeAlgorithm {

	public GrapeTreeAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	public Pair<Cluster, Cluster> select() {
		return null;
	}

	@Override
	protected void join() {

	}

}
