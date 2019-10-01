package flow.calculate.symmetric;

import data.DataSet;
import data.DistanceMatrix;
import data.Node;
import exception.NumberOfArgumentsException;
import flow.calculate.Calculator;

import java.util.List;

public abstract class SymmetricCalculator extends Calculator {

	protected SymmetricCalculator(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, number);
	}

	@Override
	public DistanceMatrix calculate(DataSet dataset) {
		return null;
	}

	protected abstract int distance(Node n1, Node n2);

}
