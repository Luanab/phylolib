package command.algorithm.bt.gcp;

public final class WPGMA extends GloballyClosestPairs {

	@Override
	protected double dissimilarity(int i, int j, int u, int k) {
		return (distance(i, k) + distance(j, k)) / 2;
	}

}
