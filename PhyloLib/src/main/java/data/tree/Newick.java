package data.tree;

import cli.Format;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Responsible for parsing {@link Tree phylogenetic trees} from and to Strings in Newick format.
 */
public class Newick extends TreeParser {

	@Override
	public Tree parse(Stream<String> data) {
		String newick = data.map(d -> d.replaceAll("\\s", "")).collect(Collectors.joining());
		Stack<List<Edge>> levels = new Stack<>();
		List<String> ids = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();
		int counter = 0;
		while (newick.length() > 0) {
			switch (newick.charAt(0)) {
				case ';':
					if (!levels.isEmpty() || edges.isEmpty())
						return null;
					break;
				case ',':
					break;
				case '(':
					levels.push(new ArrayList<>());
					break;
				case ')':
					for (Edge edge : levels.pop())
						edges.add(new Edge(counter, edge.to(), edge.distance()));
					break;
				default:
					int id = counter++;
					String info = newick.split("[),;]", 2)[0];
					newick = newick.substring(info.length());
					String[] values = info.split(":", -1);
					ids.add(values[0].isBlank() ? "_" : values[0]);
					if (values.length > 1) {
						if (newick.startsWith(";") || values.length > 2 || !Format.DISTANCE.matches(values[1]))
							return null;
						levels.peek().add(new Edge(-1, id, Double.parseDouble(values[1])));
					} else if (!newick.startsWith(";"))
						return null;
					continue;
			}
			newick = newick.substring(1);
		}
		return levels.isEmpty() && !edges.isEmpty() ? new Tree(ids.toArray(new String[0]), edges) : null;
	}

	@Override
	public String parse(Tree tree) {
		StringBuilder data = new StringBuilder();
		List<Edge> edges = tree.edges().collect(Collectors.toList());
		List<Integer> visited = new ArrayList<>();
		while (!edges.isEmpty()) {
			int root = edges.stream().map(Edge::from).filter(i -> tree.edges().noneMatch(edge -> edge.to() == i)).findFirst().orElseThrow();
			parse(edges, tree.ids(), root, data, visited);
			data.append(root >= tree.ids().length ? "_" : tree.ids()[root]).append(";");
		}
		IntStream.range(0, tree.ids().length).filter(i -> !visited.contains(i)).forEach(i -> data.append(tree.ids()[i]).append(";"));
		return data.toString();
	}

	private void parse(List<Edge> tree, String[] ids, int root, StringBuilder data, List<Integer> visited) {
		visited.add(root);
		List<Edge> edges = tree.stream().filter(e -> e.from() == root || e.to() == root).collect(Collectors.toList());
		tree.removeAll(edges);
		if (!edges.isEmpty()) {
			data.append('(');
			for (Edge edge : edges) {
				if (edge.to() == root)
					edge = new Edge(edge.to(), edge.from(), edge.distance());
				parse(tree, ids, edge.to(), data, visited);
				data.append(edge.to() >= ids.length ? "_" : ids[edge.to()]).append(':').append(edge.distance()).append(',');
			}
			data.replace(data.length() - 1, data.length(), ")");
		}
	}

}
