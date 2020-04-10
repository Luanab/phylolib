package data.tree;

import logging.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Newick implements ITreeProcessor {

	@Override
	public Tree parse(Stream<String> data) {
		Tree tree = new Tree();
		String newick = data.map(String::trim).collect(Collectors.joining());
		HashMap<Integer, List<Edge>> edges = new HashMap<>();
		int depth = 0;
		int counter = 0;
		while (newick.length() > 0) {
			switch (newick.charAt(0)) {
				case '(': depth++;
					newick = newick.substring(1);
					break;
				case ',': newick = newick.substring(1);
					break;
				case ')': depth--;
					newick = newick.substring(1);
					break;
				case ';': newick = ";";
					break;
				default:
					String info = newick.split("[),;]", 2)[0];
					newick = newick.substring(info.length());
					String[] values = info.split(":", 2);
					if (newick.length() > 0 && !newick.startsWith(";")) {
						if (values.length != 2 || values[1].isBlank() || !values[1].matches("^((-)?\\d*(\\.\\d+)?)$")) {
							Log.warning(IGNORING);
							return new Tree();
						}
						edges.computeIfAbsent(depth, k -> new ArrayList<>()).add(new Edge(-1, counter, Double.parseDouble(values[1])));
					}
					Log.info(RENAMING, values[0], counter);
					List<Edge> children = edges.remove(depth + 1);
					if (children != null)
						for (Edge edge : children)
							tree.add(new Edge(counter, edge.to(), edge.distance()));
					counter++;
			}
		}
		if (!edges.isEmpty()) {
			Log.warning(IGNORING);
			return new Tree();
		}
		return tree;
	}

	@Override
	public String format(Tree tree) {
		StringBuilder data = new StringBuilder();
		if (!tree.isEmpty()) {
			int root = tree.root();
			format(tree, root, data);
			data.append(root);
		}
		return data.append(';').toString();
	}

	private void format(Tree tree, int root, StringBuilder data) {
		List<Edge> edges = tree.get(root);
		if (edges != null) {
			data.append('(');
			for (Edge edge : edges) {
				format(tree, edge.to(), data);
				data.append(edge.to()).append(':').append(edge.distance()).append(',');
			}
			data.replace(data.length() - 1, data.length(), ")");
		}
	}

}
