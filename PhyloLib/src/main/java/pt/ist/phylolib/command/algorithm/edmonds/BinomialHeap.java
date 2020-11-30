package pt.ist.phylolib.command.algorithm.edmonds;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

final class BinomialHeap {

	private final Map<EdgeNode, Node> nodes;
	private Node head;
	private Comparator<EdgeNode> comparator;

	BinomialHeap(Comparator<EdgeNode> comparator) {
		this.head = null;
		this.comparator = comparator;
		this.nodes = new HashMap<>();
	}

	private BinomialHeap(Node head) {
		this.head = head;
		this.nodes = new HashMap<>();
		if (head != null)
			this.nodes.put(head.key, head);
	}

	void push(EdgeNode idx) {
		head = unionUtil(new BinomialHeap(new Node(idx)));
	}

	void union(BinomialHeap heap) {
		head = unionUtil(heap);
	}

	EdgeNode pop() {
		Node min = head;
		Node minPrev = null;
		Node next = min.sibling;
		Node nextPrev = min;
		while (next != null) {
			if (next.compareTo(min) < 0) {
				min = next;
				minPrev = nextPrev;
			}
			nextPrev = next;
			next = next.sibling;
		}
		removeTreeRoot(min, minPrev);
		nodes.remove(min.key);
		return min.key;
	}

	boolean isEmpty() {
		return head == null;
	}

	private void removeTreeRoot(Node root, Node prev) {
		if (root == head)
			head = root.sibling;
		else
			prev.sibling = root.sibling;
		Node newHead = null;
		Node child = root.child;
		while (child != null) {
			Node next = child.sibling;
			child.sibling = newHead;
			newHead = child;
			child = next;
		}
		head = unionUtil(new BinomialHeap(newHead));
	}

	private void linkTree(Node minNodeTree, Node other) {
		other.sibling = minNodeTree.child;
		minNodeTree.child = other;
		minNodeTree.degree++;
	}

	private Node unionUtil(BinomialHeap heap) {
		Node newHead = merge(this, heap);
		head = null;
		heap.head = null;
		if (newHead == null)
			return null;
		Node prev = null;
		Node curr = newHead;
		Node next = newHead.sibling;
		while (next != null) {
			if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
				prev = curr;
				curr = next;
			} else {
				if (curr.compareTo(next) < 0) {
					curr.sibling = next.sibling;
					linkTree(curr, next);
				} else {
					if (prev == null)
						newHead = next;
					else
						prev.sibling = next;
					linkTree(next, curr);
					curr = next;
				}
			}
			next = curr.sibling;
		}
		nodes.putAll(heap.nodes);
		heap.nodes.clear();
		return newHead;
	}

	private Node merge(BinomialHeap heap1, BinomialHeap heap2) {
		if (heap1.head == null)
			return heap2.head;
		else if (heap2.head == null)
			return heap1.head;
		Node head;
		Node heap1Next = heap1.head;
		Node heap2Next = heap2.head;
		if (heap1.head.degree <= heap2.head.degree) {
			head = heap1.head;
			heap1Next = heap1Next.sibling;
		} else {
			head = heap2.head;
			heap2Next = heap2Next.sibling;
		}
		Node tail = head;
		while (heap1Next != null && heap2Next != null) {
			if (heap1Next.degree <= heap2Next.degree) {
				tail.sibling = heap1Next;
				heap1Next = heap1Next.sibling;
			} else {
				tail.sibling = heap2Next;
				heap2Next = heap2Next.sibling;
			}
			tail = tail.sibling;
		}
		tail.sibling = heap1Next != null ? heap1Next : heap2Next;
		return head;
	}

	private final class Node {

		private final EdgeNode key;

		private int degree;
		private Node child;
		private Node sibling;

		private Node(EdgeNode key) {
			this.key = key;
		}

		private int compareTo(Node other) {
			return comparator.compare(key, other.key);
		}

	}

}