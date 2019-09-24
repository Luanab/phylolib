package flow;

import data.PhylogeneticTree;
import format.IFormatter;
import format.NewickFormatter;
import format.NexusFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

class Formatter extends Component<IFormatter, Function<PhylogeneticTree, String>> {

	Formatter() {
		this.name = "-format";
		this.number = 0;
		this.options = new HashMap<>() {{
			put("newick", new NewickFormatter());
			put("nexus", new NexusFormatter());
		}};
		this.mapper = (values, formatter) -> formatter::format;
	}

	@Override
	List<String> defaultValues() {
		return new ArrayList<>(){{
			add("newick");
		}};
	}

}
