package flow.process.mst;

import data.Cluster;
import data.Pair;

public final class GoeBURSTAlgorithm extends MinimumSpanningTreeAlgorithm {

	private final int lvs;

	public GoeBURSTAlgorithm(int lvs) {
		this.lvs = lvs;
	}

	@Override
	public Pair<Cluster, Cluster> select() {
		return null;
	}

}
