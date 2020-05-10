package command.algorithm.mst.edmonds;

import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;

public final class Forest {

	private final Map<Edge, EdgeNode> edgesInTree;
	private final EdgeNode[] pi;
	private final Set<Integer> rset;
	private final int[] maxArr;
	private final int size;

	public Forest(int size) {
		this.edgesInTree = new HashMap<>(size * size - size);
		this.pi = new EdgeNode[size];
		this.rset = new HashSet<>();
		this.maxArr = new int[size];
		this.size = size;
		for (int i = 0; i < size; i++)
			this.maxArr[i] = i;
	}

	public void add(EdgeNode edgeNode) {
		edgesInTree.put(edgeNode.getWeightedEdge(), edgeNode);
	}

	public void addPi(int p, EdgeNode node) {
		pi[p] = node;
	}

	public void addEntryToRset(int root) {
		rset.add(root);
	}

	public void updateMaxArray(int p1, int p2) {
		maxArr[p1] = maxArr[p2];
	}

	public Tree expansion(Matrix matrix) {
		Tree tree = new Tree(matrix.ids());
		List<EdgeNode> N = edgesInTree.values().stream().filter(EdgeNode::isRoot).collect(Collectors.toList());
		rset.stream().map(node -> pi[maxArr[node]]).filter(Objects::nonNull).forEach(node -> removeFromRoots(node, N));
		while (!N.isEmpty()) {
			EdgeNode edgeNode = N.remove(0);
			if (edgeNode.isRemoveF())
				continue;
			Edge edge = edgeNode.getWeightedEdge();
			tree.add(edge);
			int dst = edge.to();
			removeFromRoots(pi[dst], N);
		}
		return tree;
	}

	private void removeFromRoots(EdgeNode node, List<EdgeNode> roots) {
		for (; node != null; node = node.getParent()) {
			node.setRemoveF(true);
			for (EdgeNode child : node.getChildren()) {
				child.setParent(null);
				roots.add(child);
			}
		}
	}

}
