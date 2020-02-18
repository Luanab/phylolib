package data.tree;

import java.util.stream.Stream;

public final class NexusFormatter extends NewickFormatter {

	@Override
	public String format(Tree tree) {
		return super.format(tree); // TODO headers
	}

	@Override
	public Tree parse(Stream<String> data) {
		return super.parse(data
								   .filter(line -> line.trim().startsWith("Tree best="))
								   .map(line -> line.split("=", 2)[0]));
	}

}
