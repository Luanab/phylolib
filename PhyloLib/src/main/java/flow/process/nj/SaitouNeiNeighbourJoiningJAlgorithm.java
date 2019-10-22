package flow.process.nj;

import data.DistanceMatrix;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class SaitouNeiNeighbourJoiningJAlgorithm extends NeighbourJoiningAlgorithm {

	public SaitouNeiNeighbourJoiningJAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected double distance(DistanceMatrix matrix, int i, int j) {
		return 0;
	}

}
