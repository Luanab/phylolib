package flow.distance.asymmetric;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import flow.distance.DistanceCalculator;

import java.util.List;

public final class AsymmetricDistanceCalculator extends DistanceCalculator {

	public AsymmetricDistanceCalculator(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public DistanceMatrix calculate(DataSet dataset) {
		return null;
	}

}
