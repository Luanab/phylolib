package flow.format;

import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class NexusFormatter extends Formatter {

	public NexusFormatter(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public String format(PhylogeneticTree tree) {
		return null;
	}

}
