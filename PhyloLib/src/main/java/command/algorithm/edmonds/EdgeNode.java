package command.algorithm.edmonds;

import data.tree.Edge;

import java.util.LinkedList;
import java.util.List;

final class EdgeNode {

	private final Edge edge;
	private final List<EdgeNode> children;

	private EdgeNode parent;
	private boolean removeF;
	private EdgeNode initialParent;

	EdgeNode(Edge edge) {
		this.edge = edge;
		this.children = new LinkedList<>();
		this.removeF = false;
		this.parent = null;
		this.initialParent = null;
	}

	void addChild(EdgeNode node) {
		children.add(node);
	}

	boolean isRoot() {
		return parent == null;
	}

	Edge getEdge() {
		return edge;
	}

	EdgeNode getParent() {
		return parent;
	}

	void setParent(EdgeNode node) {
		if (initialParent == null)
			initialParent = node;
		parent = node;
	}

	List<EdgeNode> getChildren() {
		return children;
	}

	boolean isRemoveF() {
		return removeF;
	}

	void setRemoveF() {
		removeF = true;
	}

}
