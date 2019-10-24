package flow.process.mst;

import data.Cluster;
import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class GoeBURSTAlgorithm extends MinimumSpanningTreeAlgorithm {

	private static final int DEFAULT = 3;

	private final int lvs;

	public GoeBURSTAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
		this.lvs = parameters.isEmpty() ? DEFAULT : Integer.parseInt(parameters.remove(0));
	}

	@Override
	public Pair<Cluster, Cluster> select(DistanceMatrix matrix) {
		return null;
	}

	@Override
	protected Pair<Double, Double> join(DistanceMatrix matrix, Pair<Cluster, Cluster> clusters) {
		return null;
	}

}
