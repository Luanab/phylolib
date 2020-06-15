package command.algorithm.goeburst;

import cli.Option;
import cli.Options;
import command.algorithm.Algorithm;
import data.Context;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

import java.util.Comparator;
import java.util.stream.IntStream;

public class GoeBURST extends Algorithm {

	private int lvs;

	@Override
	public void init(Context context, Options options) {
		this.lvs = Integer.parseInt(options.remove(Option.LVS));
	}

	@Override
	public Tree process(Matrix matrix) {
		Tree tree = new Tree(matrix.ids());
		int size = matrix.size();
		int[][] lv = new int[size][lvs];
		int[] clusters = new int[size];
		IntStream.range(0, size)
				.peek(i -> clusters[i] = i)
				.mapToObj(i -> IntStream.range(0, size)
						.mapToObj(j -> new Edge(i, j, matrix.distance(i, j)))
						.filter(edge -> edge.distance() > 0 && edge.distance() <= lvs)
						.peek(edge -> lv[i][(int) edge.distance() - 1]++))
				.flatMap(i -> i)
				.sorted(Comparator.comparingDouble(Edge::distance).thenComparing((i, j) -> tiebreak(lv, matrix.ids(), i.from(), i.to(), j.from(), j.to())))
				.takeWhile(edge -> tree.edges().count() < size - 1)
				.filter(edge -> clusters[edge.from()] != clusters[edge.to()])
				.forEach(edge -> reduce(clusters, edge, tree));
		return tree;
	}

	private int tiebreak(int[][] lv, String[] ids, int ifrom, int ito, int jfrom, int jto) {
		int diff;
		for (int index = 0; index < lvs; index++) {
			diff = Integer.compare(Math.max(lv[jfrom][index], lv[jto][index]), Math.max(lv[ifrom][index], lv[ito][index]));
			if (diff != 0)
				return diff;
			diff = Integer.compare(Math.min(lv[jfrom][index], lv[jto][index]), Math.min(lv[ifrom][index], lv[ito][index]));
			if (diff != 0)
				return diff;
		}
		diff = Integer.compare(Math.min(ifrom, ito), Math.min(jfrom, jto));
		return diff != 0 ? diff : compare(ids[compare(ids[ifrom], ids[ito]) > 0 ? ifrom : ito], ids[compare(ids[jfrom], ids[jto]) > 0 ? jfrom : jto]);
	}

	private int compare(String i, String j) {
		return i.length() == j.length() ? i.compareTo(j) : (i.length() - j.length());
	}

	private void reduce(int[] clusters, Edge edge, Tree tree) {
		int i = clusters[edge.from()];
		int j = clusters[edge.to()];
		for (int index = 0; index < clusters.length; index++)
			if (clusters[index] == j)
				clusters[index] = i;
		tree.add(edge);
	}

}
