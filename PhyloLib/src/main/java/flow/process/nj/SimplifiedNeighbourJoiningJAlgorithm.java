package flow.process.nj;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class SimplifiedNeighbourJoiningJAlgorithm extends NeighbourJoiningAlgorithm {

	public SimplifiedNeighbourJoiningJAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

}
