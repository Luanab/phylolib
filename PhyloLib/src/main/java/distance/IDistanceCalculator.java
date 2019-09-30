package distance;

import data.DataSet;
import data.DistanceMatrix;

public interface IDistanceCalculator {

	DistanceMatrix calculate(DataSet dataset);

}
