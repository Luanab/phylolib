package flow.process.gcp;

import data.Cluster;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UPGMAAlgorithm extends GloballyClosestPairsAlgorithm {

	public UPGMAAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	public Pair<Cluster, Cluster> select() {
		return null;
	}

}
