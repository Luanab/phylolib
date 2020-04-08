package data.tree;

public final class Edge {

	private final int from;
	private final int to;
	private final double distance;

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
