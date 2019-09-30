package distance.symmetric;

import data.DataSet;
import data.DistanceMatrix;
import data.Node;
import distance.IDistanceCalculator;

public abstract class SymmetricDistanceCalculator implements IDistanceCalculator {

	@Override
	public DistanceMatrix calculate(DataSet dataset) {
		return null;
	}

	protected abstract int distance(Node n1, Node n2);

}
