package flow.write;

import data.PhylogeneticTree;

public final class NewickWriter extends Writer {

	NewickWriter(String to) {
		super(to);
	}

	@Override
	protected String format(PhylogeneticTree tree) {
		return null;
	}

}
