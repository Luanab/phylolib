package flow.distance.symmetric;

import data.DataSet;
import data.DistanceMatrix;
import data.Node;
import exception.NumberOfArgumentsException;
import flow.distance.DistanceCalculator;

import java.util.List;

public abstract class SymmetricDistanceCalculator extends DistanceCalculator {

	protected SymmetricDistanceCalculator(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, number);
	}

	@Override
	public DistanceMatrix calculate(DataSet dataset) {
		return null;
	}

	protected abstract int distance(Node n1, Node n2);

}
