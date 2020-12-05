package pt.ist.phylolib.command.optimization;

import pt.ist.phylolib.data.matrix.Matrix;
import pt.ist.phylolib.data.tree.Edge;
import pt.ist.phylolib.data.tree.Tree;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Responsible for optimizing a {@link Tree phylogenetic tree} into another using the Local Branch Recrafting algorithm.
 */
public final class LBR extends Optimization {

	@Override
	protected Edge join(int loci, Matrix matrix, Tree tree, Edge edge) {
		int w = recraft(loci, matrix, tree, edge.from(), edge.to());
		int v = recraft(loci, matrix, tree, edge.to(), edge.from());
		return new Edge(w, v, matrix.distance(w, v));
	}

	private int recraft(int loci, Matrix matrix, Tree tree, int u, int v) {
		return tree.edges()
				.map(Edge::from)
				.filter(i -> tree.edges().noneMatch(edge -> edge.to() == i))
				.map(i -> children(tree, i).collect(Collectors.toSet()))
				.filter(n -> n.contains(u))
				.flatMap(Collection::stream)
				.distinct()
				.filter(n -> !n.equals(u))
				.reduce(u, (uu, w) -> select(loci, matrix, uu, w, v));
	}

	private Stream<Integer> children(Tree tree, int i) {
		return Stream.concat(Stream.of(i), tree.edges().filter(edge -> edge.from() == i).flatMap(edge -> children(tree, edge.to())));
	}

	private int select(int loci, Matrix matrix, int u, int w, int v) {
		double uw = matrix.distance(u, w);
		double wu = matrix.distance(w, u);
		double uv = matrix.distance(u, v);
		double wv = matrix.distance(w, v);
		double a = contemporary(loci, uw, uv, wv);
		double b = ancestor(loci, wu, uv, wv);
		return (a >= b && mean(matrix, u) > mean(matrix, w)) || (a < b && uv > wv) ? w : u;
	}

	private double contemporary(int loci, double uw, double uv, double wv) {
		double l = Math.sqrt(1 - uw);
		return likelihood(loci, uw, uv, wv, l, (1 - (uv + wv) / 2) / l, l);
	}

	private double ancestor(int loci, double wu, double uv, double wv) {
		double x = 1 - ((1 - wu) * (1 - wv) + (1 - uv)) / 2;
		double y = uv - 2 * x;
		return likelihood(loci, wu, uv, wv, 1 + (x * wu) / y, 1 + (x * wv) / y, 1);
	}

	private double likelihood(int loci, double d, double uv, double wv, double l, double k, double y) {
		return loci * d * Math.log(1 - y * l) + loci * (1 - d) * Math.log(y * l)
		       + loci * uv * Math.log(1 - l * k) + loci * (1 - uv) * Math.log(l * k)
		       + loci * wv * Math.log(1 - y * k) + loci * (1 - wv) * Math.log(y * k);
	}

	private double mean(Matrix matrix, int i) {
		return (matrix.size() - 1) / IntStream.range(0, matrix.size()).filter(j -> j != i).mapToDouble(j -> 1 / matrix.distance(i, j)).sum();
	}

}
