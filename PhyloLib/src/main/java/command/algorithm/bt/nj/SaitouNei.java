package command.algorithm.bt.nj;

public final class SaitouNei extends NeighbourJoining {

	@Override
	protected int weight(int i) {
		return 1;
	}

	@Override
	protected double lambda(int i, int j) {
		return 0.5;
	}

	@Override
	protected double length(double distance) {
		return 0;
	}

}
