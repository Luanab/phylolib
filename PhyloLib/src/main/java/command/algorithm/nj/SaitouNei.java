package command.algorithm.nj;

public final class SaitouNei extends NeighbourJoining {

	@Override
	protected int weight(Cluster i) {
		return 1;
	}

	@Override
	protected double lambda(Cluster ci, Cluster cj) {
		return 0.5;
	}

	@Override
	protected double length(double distance) {
		return 0;
	}

}
