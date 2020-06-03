package command.algorithm.nj;

public final class UNJ extends NeighbourJoining {

	@Override
	protected int weight(Cluster i) {
		return i.elements;
	}

	@Override
	protected double lambda(Cluster ci, Cluster cj) {
		return ci.elements / (double) (ci.elements + cj.elements);
	}

	@Override
	protected double length(double distance) {
		return distance;
	}

}
