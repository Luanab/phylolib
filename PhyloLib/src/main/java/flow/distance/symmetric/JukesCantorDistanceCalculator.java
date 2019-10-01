package flow.distance.symmetric;

import data.Node;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class JukesCantorDistanceCalculator extends SymmetricDistanceCalculator {

	public JukesCantorDistanceCalculator(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	protected int distance(Node n1, Node n2) {
		return 0;
	}

}
