package flow.calculate.asymmetric;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import flow.calculate.Calculator;

import java.util.List;

public final class AsymmetricCalculator extends Calculator {

	public AsymmetricCalculator(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public DistanceMatrix calculate(DataSet dataset) {
		return null;
	}

}
