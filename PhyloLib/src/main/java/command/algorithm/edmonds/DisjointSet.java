package command.algorithm.edmonds;

class DisjointSet {

	protected final int size;
	protected final int[] pi;
	protected final int[] rank;

	DisjointSet(int n) {
		this.size = n + 1;
		this.pi = new int[size];
		this.rank = new int[size];
		for (int i = 0; i < this.size; i++)
			this.pi[i] = i;
	}

	int findSet(int i) {
		for (; i != pi[i]; i = pi[i])
			pi[i] = pi[pi[i]];
		return i;
	}

	boolean sameSet(int i, int j) {
		return findSet(i) == findSet(j);
	}

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
		}
	}

}