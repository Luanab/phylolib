package flow.process.gcp;

import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class GloballyClosestPairsAlgorithm extends Algorithm {

	protected GloballyClosestPairsAlgorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	@Override
	public final void init() {
		// ignore this step
	}

	@Override
	public final void join() {

	}

	@Override
	public final void reduce() {

	}

}
