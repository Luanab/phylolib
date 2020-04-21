package command.algorithm.bt.nj;

public final class StudierKeppler extends NeighbourJoining {

	@Override
	protected double lambda(int i, int j) {
		return 0.5;
	}

	@Override
	protected double length(int i, int j) {
		return distance(i, j);
	}

}
