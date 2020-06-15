package command.algorithm.edmonds;

import data.tree.Edge;

import java.util.LinkedList;
import java.util.List;

public final class EdgeNode {

	private final Edge edge;
	private final List<EdgeNode> children;

	private EdgeNode parent;
	private boolean removeF;
	private EdgeNode initialParent;

	public EdgeNode(Edge edge) {
		this.edge = edge;
		this.children = new LinkedList<>();
		this.removeF = false;
		this.parent = null;
		this.initialParent = null;
	}

	public void addChild(EdgeNode node) {
		children.add(node);
	}

	public boolean isRoot() {
		return parent == null;
	}

	public Edge getEdge() {
		return edge;
	}

	public EdgeNode getParent() {
		return parent;
	}

	public void setParent(EdgeNode node) {
		if (initialParent == null)
			initialParent = node;
		parent = node;
	}

	public List<EdgeNode> getChildren() {
		return children;
	}

	public boolean isRemoveF() {
		return removeF;
	}

	public void setRemoveF(boolean value) {
		removeF = value;
	}

}
