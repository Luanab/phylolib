package data.tree;

import logging.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Newick implements ITreeProcessor {

	@Override
	public Tree parse(Stream<String> data) {
		String newick = data.map(String::trim).collect(Collectors.joining());
		Stack<List<Edge>> levels = new Stack<>();
		List<String> ids = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		int counter = 0;
		while (newick.length() > 0) {
			switch (newick.charAt(0)) {
				case ';': newick = "";
					break;
				case ',': newick = newick.substring(1);
					break;
				case '(': levels.push(new ArrayList<>());
					newick = newick.substring(1);
					break;
				case ')':
					for (Edge edge : levels.pop())
						edges.add(new Edge(counter, edge.to(), edge.distance()));
					newick = newick.substring(1);
				default:
					int id = counter++;
					String info = newick.split("[),;]", 2)[0];
					newick = newick.substring(info.length());
					String[] values = info.split(":", 2);
					ids.add(values[0].isBlank() ? "_" : values[0]);
					if (newick.length() > 0 && !newick.startsWith(";")) {
						if (values.length != 2 || values[1].isBlank() || !values[1].matches("^((-)?\\d*(\\.\\d+)?)$")) {
							Log.warning(INVALID);
							return new Tree();
						}
						levels.peek().add(new Edge(-1, id, Double.parseDouble(values[1])));
					}
			}
		}
		if (!levels.isEmpty()) {
			Log.warning(INVALID);
			return new Tree();
		}
		return new Tree(ids.toArray(new String[0]), edges);
	}

	@Override
	public String format(Tree tree) {
		StringBuilder data = new StringBuilder();
		if (!tree.isEmpty()) {
			int root = tree.root();
			format(tree, root, data);
			data.append(id(tree, root));
		}
		return data.append(';').toString();
	}

	private void format(Tree tree, int root, StringBuilder data) {
		List<Edge> edges = tree.remove(root);
		if (!edges.isEmpty()) {
			data.append('(');
			for (Edge edge : edges) {
				if (edge.to() == root)
					edge = new Edge(edge.to(), edge.from(), edge.distance());
				format(tree, edge.to(), data);
				data.append(id(tree, edge.to())).append(':').append(edge.distance()).append(',');
			}
			data.replace(data.length() - 1, data.length(), ")");
		}
	}

	private String id(Tree tree, int i) {
		return i >= tree.ids().length ? "_" : tree.ids()[i];
	}

}
