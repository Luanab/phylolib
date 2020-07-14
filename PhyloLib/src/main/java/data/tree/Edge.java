package data.tree;

/**
 * Represents an edge as from and to nodes with a distance between them.
 */
public final class Edge {

	private final int from;
	private final int to;
	private final double distance;

	/**
	 * Creates an edge corresponding to the given from and to nodes with the given distance.
	 *
	 * @param from     the node where this edge starts
	 * @param to       the node where this edge ends
	 * @param distance the distance between the two given nodes
	 */
	public Edge(int from, int to, double distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	public int from() {
		return from;
	}

	public int to() {
		return to;
	}

	public double distance() {
		return distance;
	}

}
