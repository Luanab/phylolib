package flow.process.mst;

import exception.NumberOfArgumentsException;
import flow.process.Algorithm;

import java.util.List;

public abstract class MinimumSpanningTreeAlgorithm extends Algorithm {

	protected MinimumSpanningTreeAlgorithm(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
		super(name, value, parameters, mandatory);
	}

	@Override
	public final void init() {
		// ignore this step
	}

	@Override
	public final void reduce() {
		// ignore this step
	}

}
