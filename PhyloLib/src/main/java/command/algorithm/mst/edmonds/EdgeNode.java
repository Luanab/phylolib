package command.algorithm.mst.edmonds;

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
		this.removeF = false;
		this.parent = null;
		this.initialParent = null;
		this.children = new LinkedList<>();
	}

	public void addChild(EdgeNode edgeNode) {
		children.add(edgeNode);
	}

	public boolean isRoot() {
		return parent == null;
	}

	public Edge getWeightedEdge() {
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
