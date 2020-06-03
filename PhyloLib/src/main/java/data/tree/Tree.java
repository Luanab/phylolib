package data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class Tree {

	private final String[] ids;
	private final List<Edge> edges;

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

	public Stream<Edge> edges() {
		return edges.stream();
	}

	public void add(Edge edge) {
		edges.add(edge);
	}

	public void remove(Edge edge) {
		edges.remove(edge);
	}

}
