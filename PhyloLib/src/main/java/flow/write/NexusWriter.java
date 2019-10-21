package flow.write;

import data.PhylogeneticTree;

public final class NexusWriter extends Writer {

	NexusWriter(String to) {
		super(to);
	}

	@Override
	protected String format(PhylogeneticTree tree) {
		return null;
	}

}
