package flow.algorithm.mst;

import exception.NumberOfArgumentsException;
import flow.algorithm.DistanceMatrixAlgorithm;

import java.util.List;

public abstract class MinimumSpanningTreeAlgorithm extends DistanceMatrixAlgorithm {

	protected MinimumSpanningTreeAlgorithm(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, number);
	}

	@Override
	public final void join() {

	}

	@Override
	public final void reduce() {
		// ignore this step
	}

}
