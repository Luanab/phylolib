package command.algorithm.bt.nj;

public final class UNJ extends NeighbourJoining {

	@Override
	protected int weight(int i) {
		return elements(i);
	}

	@Override
	protected strictfp double lambda(int i, int j) {
		double count = elements(i);
		return count / (count + elements(j));
	}

	@Override
	protected double length(int i, int j) {
		return distance(i, j);
	}

}
