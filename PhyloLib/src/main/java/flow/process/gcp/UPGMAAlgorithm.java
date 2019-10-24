package flow.process.gcp;

import data.DistanceMatrix;
import data.Pair;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class UPGMAAlgorithm extends GloballyClosestPairsAlgorithm {

	public UPGMAAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	public void reduce(DistanceMatrix matrix, Pair<Double, Double> distances) {

	}

}
