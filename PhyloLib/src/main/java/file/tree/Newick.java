package file.tree;

import data.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Newick implements ITreeFormatter {

	private void format(Tree tree, StringBuilder data) {
		if (tree.getChildren() != null) {
			data.append('(');
			for (Tree child : tree.getChildren()) {
				format(child, data);
				data.append(',');
			}
			data.replace(data.length() - 1, data.length(), ")");
		}
		if (tree.getId() != null)
			data.append(tree.getId());
		if (tree.getDistance() != null)
			data.append(':').append(tree.getDistance());
	}

	@Override
	public String format(Tree tree) {
		StringBuilder data = new StringBuilder();
		format(tree, data);
		return data.append(';').toString();
	}

	@Override
	public Tree parse(Stream<String> data) {
		String newick = data.findFirst().get();
		HashMap<Integer, List<Tree>> trees = new HashMap<>();
		int depth = 0;
		while (newick.length() > 0) {
			switch (newick.charAt(0)) {
				case '(': depth++;
					break;
				case ',': break;
				case ')': depth--;
					break;
				case ';': newick = ";";
					break;
				default:
					String info = newick.split("[),;]", 2)[0];
					newick = newick.substring(info.length());
					String[] values = info.split(":", 2);
					String id = values[0];
					Double distance = values.length == 1 || values[1].isBlank() ? null : Double.parseDouble(values[1]);
					List<Tree> children = trees.remove(depth + 1);
					trees.putIfAbsent(depth, new ArrayList<>());
					trees.get(depth).add(new Tree(id, distance, children));
					continue;
			}
			newick = newick.substring(1);
		}
		return trees.get(0).get(0);
	}

}
