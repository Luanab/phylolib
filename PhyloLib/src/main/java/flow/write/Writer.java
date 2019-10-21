package flow.write;

import data.PhylogeneticTree;
import exception.ParameterException;
import flow.Option;
import flow.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Writer {

	private final String to;

	protected Writer(String to) {
		this.to = to;
	}

	protected abstract String format(PhylogeneticTree tree);

	public final void write(PhylogeneticTree tree) throws IOException {
		Files.write(Paths.get(to), format(tree).getBytes());
	}

	public static Writer get(Parameters parameters) throws ParameterException {
		return parameters.map("-writer", "-w", new ArrayList<>(){{ add("newick"); }}, new HashMap<>() {{
			put("newick", new Option<>(0, values -> new NewickWriter(parameters.to)));
			put("nexus", new Option<>(0, values -> new NexusWriter(parameters.to)));
		}});
	}

}
