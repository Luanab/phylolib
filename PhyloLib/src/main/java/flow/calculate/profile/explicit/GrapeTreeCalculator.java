package flow.calculate.profile.explicit;

import data.Profile;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class GrapeTreeCalculator extends ExplicitCalculator {

	public GrapeTreeCalculator(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected double distance(Profile a, Profile b) {
		return 0;
	}

}
