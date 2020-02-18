package data.tree;

import java.util.List;

public final class Tree {

	private final String id;
	private final Double distance;
	private final List<Tree> children;

	public Tree(String id, Double distance, List<Tree> children) {
		this.id = id;
		this.distance = distance;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public Double getDistance() {
		return distance;
	}

	public List<Tree> getChildren() {
		return children;
	}

}
