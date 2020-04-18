package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Tree {

	private final String[] ids;
	private final List<Edge> edges;

	public Tree() {
		this.ids = new String[0];
		this.edges = new ArrayList<>();
	}

	public Tree(String[] ids) {
		this.ids = ids;
		this.edges = new ArrayList<>();
	}

	public Tree(String[] ids, List<Edge> edges) {
		this.ids = ids;
		this.edges = edges;
	}

	public String[] ids() {
		return ids;
	}

	public boolean isEmpty() {
		return edges.isEmpty();
	}

	public void add(Edge edge) {
		edges.add(edge);
	}

	public List<Edge> get(int i) {
		return edges.stream().filter(e -> e.from() == i).collect(Collectors.toList());
	}

	public int root() {
		return edges.stream()
				.map(Edge::from)
				.filter(i -> edges.stream().noneMatch(edge -> edge.to() == i))
				.findFirst()
				.orElse(0);
	}

	public List<Edge> edges() {
		return edges;
	}

}
