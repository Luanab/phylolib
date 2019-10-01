package flow.calculate;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.calculate.asymmetric.AsymmetricCalculator;
import flow.calculate.symmetric.HammingCalculator;
import flow.calculate.symmetric.JukesCantorCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Calculator extends Component {

	private static final String NAME = "-calculator";

	protected Calculator(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract DistanceMatrix calculate(DataSet dataset);

	public static Calculator get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, new ArrayList<>(){{ add("hamming"); }}, new HashMap<>() {{
			put("hamming", HammingCalculator::new);
			put("jukescantor", JukesCantorCalculator::new);
			put("asymmetric", AsymmetricCalculator::new);
		}});
	}

}
