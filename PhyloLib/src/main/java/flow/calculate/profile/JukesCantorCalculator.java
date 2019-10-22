package flow.calculate.profile;

import data.Profile;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class JukesCantorCalculator extends ProfileCalculator {

	public JukesCantorCalculator(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected int distance(Profile a, Profile b) {
		return 0;
	}

}
