package flow.write;

import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class NexusWriter extends Writer {

	NexusWriter(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
		super(name, value, parameters, 0);
	}

	@Override
	protected String format(PhylogeneticTree tree) {
		return null;
	}

}
