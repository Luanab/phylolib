package data.tree;

public final class Edge {

	private final int source;
	private final int target;
	private final double distance;

	public Edge(int source, int target, double distance) {
		this.source = source;
		this.target = target;
		this.distance = distance;
	}

	public int getSource() {
		return source;
	}

	public int getTarget() {
		return target;
	}

	public double getDistance() {
		return distance;
	}

}
