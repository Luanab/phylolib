package flow.single;

import data.DataSet;
import data.DistanceMatrix;
import distance.IDistanceCalculator;
import distance.asymmetric.AsymmetricDistanceCalculator;
import distance.symmetric.HammingDistanceCalculator;
import distance.symmetric.JukesCantorDistanceCalculator;
import exception.ParameterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistanceCalculator extends SingleComponent<IDistanceCalculator> {

	public DistanceCalculator(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		super(parameters, "-distance", 0, new ArrayList<>(){{ add("hamming"); }}, new HashMap<>() {{
			put("hamming", new HammingDistanceCalculator());
			put("jukescantor", new JukesCantorDistanceCalculator());
			put("asymmetric", new AsymmetricDistanceCalculator());
		}});
	}

	public DistanceMatrix calculate(DataSet dataset) {
		return option.calculate(dataset);
	}

}
