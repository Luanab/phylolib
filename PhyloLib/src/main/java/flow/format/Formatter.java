package flow.format;

import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Formatter extends Component {

	private static final String NAME = "-format";

	protected Formatter(List<String> values, int number) throws NumberOfArgumentsException {
		super(values, NAME, number);
	}

	public abstract String format(PhylogeneticTree tree);

	public static Formatter get(HashMap<String, List<List<String>>> parameters) throws ParameterException {
		return Component.getSingle(parameters, NAME, new ArrayList<>(){{ add("newick"); }}, new HashMap<>() {{
			put("newick", NewickFormatter::new);
			put("nexus", NexusFormatter::new);
		}});
	}

}
