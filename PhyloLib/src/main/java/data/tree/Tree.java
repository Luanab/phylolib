package data.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Tree {

	private final Map<Integer, List<Edge>> edges = new HashMap<>();

	public boolean isEmpty() {
		return edges.isEmpty();
	}

	public void add(Edge edge) {
		edges.computeIfAbsent(edge.from(), k -> new ArrayList<>()).add(edge);
	}

	public List<Edge> get(int id) {
		return edges.get(id);
	}

	public int root() {
		return edges.keySet().stream()
				.filter(id -> edges.values().stream().noneMatch(edges -> edges.stream().anyMatch(edge -> edge.to() == id)))
				.findFirst()
				.orElse(0);
	}

}
