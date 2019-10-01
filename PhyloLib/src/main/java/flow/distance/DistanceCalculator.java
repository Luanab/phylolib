package flow.distance;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.distance.asymmetric.AsymmetricDistanceCalculator;
import flow.distance.symmetric.HammingDistanceCalculator;
import flow.distance.symmetric.JukesCantorDistanceCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DistanceCalculator extends Component {

	private static final String NAME = "-distance";

	protected DistanceCalculator(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract DistanceMatrix calculate(DataSet dataset);

	public static DistanceCalculator get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, new ArrayList<>(){{ add("hamming"); }}, new HashMap<>() {{
			put("hamming", HammingDistanceCalculator::new);
			put("jukescantor", JukesCantorDistanceCalculator::new);
			put("asymmetric", AsymmetricDistanceCalculator::new);
		}});
	}

}
