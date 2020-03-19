package data.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public final class Nexus extends Newick {

	@Override
	public Tree parse(Stream<String> data) {
		Iterator<String> iterator = data
				.dropWhile(line -> !line.equals("BEGIN TREES;"))
				.takeWhile(line -> !line.equals("END;"))
				.dropWhile(line -> !line.contains("("))
				.iterator();
		List<String> lines = new ArrayList<>();
		String next;
		if (iterator.hasNext()) {
			next = iterator.next();
			lines.add(next.substring(next.indexOf('(')));
		}
		while (iterator.hasNext()) {
			lines.add(next = iterator.next());
			if (next.contains(";"))
				break;
		}
		return super.parse(lines.stream());
	}

	@Override
	public String format(Tree tree) {
		StringBuilder data = new StringBuilder("BEGIN TAXA;\n\tTaxLabels");
		ids(tree, data);
		data.append("\nEND;\nBEGIN TREES;\n\tTree result = ")
				.append(super.format(tree))
				.append("\nEND;");
		return data.toString();
	}

	private void ids(Tree tree, StringBuilder data) {
		if (tree.getId() != null)
			data.append(' ').append(tree.getId());
		if (tree.getChildren() != null)
			tree.getChildren().forEach(child -> ids(child, data));
	}

}
