package data.tree;

import java.util.List;

public final class Tree {

	private String id;
	private Double distance;
	private List<Tree> children;

	public Tree() {
	}

	public Tree(String id, Double distance, List<Tree> children) {
		this.id = id;
		this.distance = distance;
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

}
