package command.algorithm.mst.edmonds;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.*;

public final class Edmonds extends Algorithm {

	private Comparator<EdgeNode> maxDisjointCmp;
	private BinomialHeap[] queues;
	private DisjointSet weaklyConnected;
	private FasterDirectedMSTDisjointSet stronglyConnected;
	private List<Integer> roots;
	private Forest forest;
	private EdgeNode[] inEdgeNode;
	private List<List<EdgeNode>> edgeNodeCycle;

	@Override
	public Tree process(Matrix matrix) {
		init(matrix);
		while (!roots.isEmpty()) {
			int root = roots.remove(0);
			EdgeNode min = getMinEdgeNode(root);
			if (min == null)
				continue;
			processCameriniForest(min, root);
			int u = min.getWeightedEdge().from();
			int v = min.getWeightedEdge().to();
			if (weaklyConnected.findSet(u) != weaklyConnected.findSet(v)) {
				inEdgeNode[root] = min;
				weaklyConnected.unionSet(u, v);
			} else
				contract(u, v, root, min);
		}
		return forest.expansion(matrix);
	}

	public void init(Matrix matrix) {
		int size = matrix.size();
		Comparator<Edge> cmp = Comparator.comparingDouble(this::getAdjustedWeight)
				.thenComparingInt(i -> Integer.min(i.from(), i.to()))
				.thenComparingInt(i -> Integer.max(i.from(), i.to()));
		this.maxDisjointCmp = (i, j) -> cmp.compare(i.getWeightedEdge(), (j.getWeightedEdge()));
		this.stronglyConnected = new FasterDirectedMSTDisjointSet(size);
		this.weaklyConnected = new DisjointSet(size);
		this.queues = new BinomialHeap[size];
		this.inEdgeNode = new EdgeNode[size];
		this.forest = new Forest(size);
		this.roots = new ArrayList<>(size);
		this.edgeNodeCycle = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			this.roots.add(i);
			this.queues[i] = new BinomialHeap(this.maxDisjointCmp);
			this.edgeNodeCycle.add(i, null);
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				if (i != j)
					queues[j].push(new EdgeNode(new Edge(i, j, matrix.distance(i, j))));
		}
	}

	private void contract(int u, int v, int root, EdgeNode min) {
		List<Integer> contractionSet = new ArrayList<>();
		contractionSet.add(stronglyConnected.findSet(v));
		List<EdgeNode> nodes = new ArrayList<>();
		nodes.add(min);
		Map<Integer, EdgeNode> map = new HashMap<>();
		map.put(stronglyConnected.findSet(v), min);
		inEdgeNode[root] = null;
		for (int i = stronglyConnected.findSet(u); inEdgeNode[i] != null; i = stronglyConnected.findSet(inEdgeNode[i].getWeightedEdge().from())) {
			map.put(i, inEdgeNode[i]);
			nodes.add(inEdgeNode[i]);
			contractionSet.add(i);
		}
		Edge edge = Collections.max(nodes, maxDisjointCmp).getWeightedEdge();
		int dst = stronglyConnected.findSet(edge.to());
		double max = getAdjustedWeight(edge);
		for (Integer node : contractionSet)
			stronglyConnected.addWeight(node, max - getAdjustedWeight(map.get(node).getWeightedEdge()));
		for (EdgeNode node : nodes)
			stronglyConnected.unionSet(node.getWeightedEdge().from(), node.getWeightedEdge().to());
		int rep = stronglyConnected.findSet(edge.to());
		roots.add(0, rep);
		performHeapUnion(rep, contractionSet);
		forest.updateMaxArray(rep, dst);
		edgeNodeCycle.set(rep, nodes);
	}

	private void performHeapUnion(int rep, List<Integer> contractionSet) {
		BinomialHeap heap = queues[rep];
		for (Integer node : contractionSet)
			if (rep != node)
				heap.union(queues[node]);
	}

	private EdgeNode getMinEdgeNode(int root) {
		BinomialHeap pq = queues[root];
		if (pq.isEmpty()) {
			forest.addEntryToRset(root);
			return null;
		}
		EdgeNode minEdgeNode = pq.pop();
		Edge min = minEdgeNode.getWeightedEdge();
		while (!pq.isEmpty() && stronglyConnected.sameSet(min.from(), min.to())) {
			minEdgeNode = pq.pop();
			min = minEdgeNode.getWeightedEdge();
		}
		if (stronglyConnected.sameSet(min.from(), min.to())) {
			forest.addEntryToRset(root);
			return null;
		}
		return minEdgeNode;
	}

	private void processCameriniForest(EdgeNode minEdgeNode, int root) {
		forest.add(minEdgeNode);
		if (edgeNodeCycle.get(root) == null)
			forest.addPi(root, minEdgeNode);
		else
			for (EdgeNode node : edgeNodeCycle.get(root)) {
				node.setParent(minEdgeNode);
				minEdgeNode.addChild(node);
			}
	}

	private double getAdjustedWeight(Edge edge) {
		return edge.distance() + stronglyConnected.findWeight(edge.to());
	}

}
