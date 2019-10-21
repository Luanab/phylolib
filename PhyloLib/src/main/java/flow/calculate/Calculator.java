package flow.calculate;

import data.DataSet;
import data.DistanceMatrix;
import exception.ParameterException;
import flow.Option;
import flow.Parameters;
import flow.calculate.file.FileCalculator;
import flow.calculate.profile.GrapeTreeCalculator;
import flow.calculate.profile.HammingCalculator;
import flow.calculate.profile.JukesCantorCalculator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Calculator {

	public abstract DistanceMatrix calculate(DataSet dataset) throws IOException;

	public static Calculator get(Parameters parameters) throws ParameterException {
		return parameters.map("-calculator", "-c", new ArrayList<>(){{ add("hamming"); }}, new HashMap<>() {{
			put("hamming", new Option<>(0, values -> new HammingCalculator()));
			put("jukescantor", new Option<>(0, values -> new JukesCantorCalculator()));
			put("grapetree", new Option<>(0, values -> new GrapeTreeCalculator()));
			put("file", new Option<>(1, values -> new FileCalculator(values.get(0))));
		}});
	}

}
