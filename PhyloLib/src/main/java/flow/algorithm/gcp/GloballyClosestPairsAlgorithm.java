package flow.algorithm.gcp;

import exception.NumberOfArgumentsException;
import flow.algorithm.DistanceMatrixAlgorithm;

import java.util.List;

abstract class GloballyClosestPairsAlgorithm extends DistanceMatrixAlgorithm {

	protected GloballyClosestPairsAlgorithm(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, number);
	}

	@Override
	public final void join() {

	}

	@Override
	public final void reduce() {

	}

}
