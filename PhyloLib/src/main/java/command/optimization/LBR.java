package command.optimization;

import cli.Options;
import data.Context;
import data.dataset.Dataset;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class LBR extends Optimization {

	private int loci;
	private Matrix matrix;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		Dataset dataset = context.getDataset(options);
		this.loci = dataset.profile(0).size();
		this.matrix = context.getMatrix(options);
	}

	@Override
	public final Tree process(Tree tree) {
		Set<Edge> edges = new HashSet<>(tree.edges());
		while (!edges.isEmpty()) {
			Edge previous = edges.stream().min(Comparator.comparingDouble(Edge::distance)).orElseThrow();
			tree.remove(previous);
			int u = recraft(tree, previous.from(), previous.to());
			int v = recraft(tree, previous.to(), previous.from());
			Edge current = new Edge(u, v, matrix.distance(u, v));
			edges.remove(previous);
			tree.add(current);
			if (previous.distance() > current.distance())
				edges.add(current);
		}
		return tree;
	}

	private int recraft(Tree tree, int u, int v) {
		for (Integer w : neighbours(tree, v)) {
			double uw = matrix.distance(u, w);
			double wu = matrix.distance(w, u);
			double uv = matrix.distance(u, v);
			double wv = matrix.distance(w, v);
			double a = contemporary(uw, uv, wv);
			double b = ancestor(wu, uv, wv);
			if ((a >= b && mean(u) > mean(w)) || (a < b && uv > wv))
				u = w;
		}
		return u;
	}

	private Set<Integer> neighbours(Tree tree, int u) {
		return tree.edges().stream()
				.map(Edge::from)
				.filter(i -> tree.edges().stream().noneMatch(edge -> edge.to() == i))
				.map(i -> children(tree, i).collect(Collectors.toSet()))
				.filter(neighbours -> neighbours.remove(u))
				.findFirst()
				.orElseThrow();
	}

	private Stream<Integer> children(Tree tree, int i) {
		return Stream.concat(Stream.of(i), tree.edges().stream().filter(edge -> edge.from() == i).flatMap(edge -> children(tree, edge.to())));
	}

	private double contemporary(double uw, double uv, double wv) {
		double l = Math.sqrt(1 - uw);
		return likelihood(uw, uv, wv, l, (1 - (uv + wv) / 2) / l, l);
	}

	private double ancestor(double wu, double uv, double wv) {
		double x = 1 - ((1 - wu) * (1 - wv) + (1 - uv)) / 2;
		double y = uv - 2 * x;
		return likelihood(wu, uv, wv, 1 + (x * wu) / y, 1 + (x * wv) / y, 1);
	}

	private double likelihood(double d, double uv, double wv, double l, double k, double y) {
		return loci * d * Math.log(1 - y * l) + loci * (1 - d) * Math.log(y * l)
			   + loci * uv * Math.log(1 - l * k) + loci * (1 - uv) * Math.log(l * k)
			   + loci * wv * Math.log(1 - y * k) + loci * (1 - wv) * Math.log(y * k);
	}

	private double mean(int i) {
		return (matrix.size() - 1) / IntStream.range(0, matrix.size()).filter(j -> j != i).mapToDouble(j -> 1 / matrix.distance(i, j)).sum();
	}

}
