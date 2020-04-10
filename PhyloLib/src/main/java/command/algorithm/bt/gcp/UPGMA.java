package command.algorithm.bt.gcp;

public final class UPGMA extends GloballyClosestPairs {

	@Override
	protected strictfp double dissimilarity(int i, int j, int u, int k) {
		long ci = elements(i);
		long cj = elements(j);
		return (distance(i, k) * ci + distance(j, k) * cj) / (ci + cj);
	}

}
