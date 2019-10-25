package flow.calculate.profile.implicit;

import data.Profile;
import exception.NumberOfArgumentsException;
import flow.calculate.profile.explicit.ExplicitCalculator;

import java.util.List;

public final class JukesCantorCalculator extends ImplicitCalculator {

	public JukesCantorCalculator(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected double distance(Profile a, Profile b) {
		return 0;
	}

}
