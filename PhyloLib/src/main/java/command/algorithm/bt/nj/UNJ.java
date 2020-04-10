package command.algorithm.bt.nj;

public final class UNJ extends NeighbourJoining {

	@Override
	protected long clusters(int u) {
		return 2;
	}

	@Override
	protected double weight(int k) {
		return 1;
	}

	@Override
	protected strictfp double lambda(int i, int j) {
		double count = elements(i);
		return count / (count + elements(j));
	}

}
