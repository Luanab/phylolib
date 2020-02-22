package data.tree;

import java.util.stream.Stream;

public final class NexusFormatter extends NewickFormatter {

	private void format(Tree tree, StringBuilder data) {
		data.append(' ');
		data.append(tree.getId());
		tree.getChildren().forEach(child -> format(child, data));
	}

	@Override
	public String format(Tree tree) {
		StringBuilder data = new StringBuilder("BEGIN TAXA;\nTAXLABELS");
		format(tree, data);
		data.append("\nEND;\nBEGIN TREES;\n\tTree result = ")
				.append(super.format(tree))
				.append(";\nEND;");
		return data.toString();
	}

	@Override
	public Tree parse(Stream<String> data) {
		return super.parse(data
				.dropWhile(line -> !line.equals("BEGIN TREES;"))
				.filter(line -> line.trim().contains("("))
				.map(line -> line.substring(line.indexOf('('))));
	}

}
