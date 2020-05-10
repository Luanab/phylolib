package command.algorithm.mst.edmonds;

public final class FasterDirectedMSTDisjointSet extends DisjointSet {

	private final double[] weight;

	public FasterDirectedMSTDisjointSet(int n) {
		super(n);
		this.weight = new double[this.size];
	}

	public double findWeight(int i) {
		return i != pi[i] ? weight[i] + weight[pi[i]] : weight[i];
	}

	public void addWeight(int i, double w) {
		weight[findSet(i)] += w;
	}

	@Override
	public int findSet(int i) {
		for (; pi[i] != pi[pi[i]]; i = pi[i]) {
			weight[i] = weight[i] + weight[pi[i]];
			pi[i] = pi[pi[i]];
		}
		return pi[i];
	}

	@Override
	public void unionSet(int i, int j) {
		i = findSet(i);
		j = findSet(j);
		if (i == j)
			return;
		if (rank[i] > rank[j]) {
			pi[j] = i;
			weight[i] -= weight[j];
		} else if (rank[i] < rank[j]) {
			pi[i] = j;
			weight[i] -= weight[j];
		} else {
			pi[i] = j;
			weight[i] -= weight[j];
			rank[j]++;
		}
	}

}
