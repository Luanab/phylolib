package flow;

import data.PhylogeneticTree;
import exception.ParameterException;
import format.IFormatter;
import format.NewickFormatter;
import format.NexusFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Formatter extends Component<IFormatter> {

	Formatter(HashMap<String, List<String>> parameters) throws ParameterException {
		super(parameters, "-format", 0, new ArrayList<>(){{ add("newick"); }}, new HashMap<>() {{
			put("newick", new NewickFormatter());
			put("nexus", new NexusFormatter());
		}});
	}

	String format(PhylogeneticTree tree) {
		return option.format(tree);
	}

}
