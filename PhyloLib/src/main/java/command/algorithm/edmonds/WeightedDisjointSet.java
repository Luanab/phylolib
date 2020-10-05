package command.algorithm.edmonds;

final class WeightedDisjointSet extends DisjointSet {

	private final double[] weight;

	WeightedDisjointSet(int n) {
		super(n);
		this.weight = new double[this.size];
	}

	double findWeight(int i) {
		return i != pi[i] ? weight[i] + weight[pi[i]] : weight[i];
	}

	void addWeight(int i, double w) {
		weight[findSet(i)] += w;
	}

	@Override
	int findSet(int i) {
		for (; pi[i] != pi[pi[i]]; i = pi[i]) {
			weight[i] += weight[pi[i]];
			pi[i] = pi[pi[i]];
		}
		return pi[i];
	}

	@Override
	void unionSet(int i, int j) {
		i = findSet(i);
		j = findSet(j);
		if (i != j) {
			if (rank[i] > rank[j])
				pi[j] = i;
			else {
				pi[i] = j;
				if (rank[i] == rank[j])
					rank[j]++;
			}
			weight[i] -= weight[j];
		}
	}

}
