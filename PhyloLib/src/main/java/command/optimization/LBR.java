package command.optimization;

import cli.Options;
import data.Context;
import data.dataset.Dataset;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Responsible for optimizing a {@link Tree phylogenetic tree} into another using the Local Branch Recrafting algorithm.
 */
public final class LBR extends Optimization {

	private int loci;
	private String[] ids;
	private Matrix matrix;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		Dataset dataset = context.getDataset(options);
		this.loci = dataset.profile(0).size();
		this.ids = dataset.ids();
		this.matrix = context.getMatrix(options);
	}

	@Override
	public final Tree process(Tree tree) {
		Tree result = new Tree(ids);
		PriorityQueue<Edge> edges = new PriorityQueue<>(Comparator.comparingDouble(Edge::distance));
		Map<Integer, Integer> map = new HashMap<>(ids.length);
		for (int i = 0; i < tree.ids().length; i++) {
			String id = tree.ids()[i];
			map.put(i, IntStream.range(0, ids.length).filter(j -> ids[j].equals(id)).findFirst().orElseThrow());
		}
		tree.edges().map(e -> new Edge(map.get(e.from()), map.get(e.to()), e.distance())).peek(result::add).forEach(edges::add);
		while (!edges.isEmpty()) {
			Edge previous = edges.remove();
			result.remove(previous);
			int u = recraft(result, previous.from(), previous.to());
			int v = recraft(result, previous.to(), previous.from());
			Edge current = new Edge(u, v, matrix.distance(u, v));
			result.add(current);
			if (current.distance() > previous.distance())
				edges.add(current);
		}
		return result;
	}

	private int recraft(Tree tree, int u, int v) {
		return tree.edges()
				.map(Edge::from)
				.filter(i -> tree.edges().noneMatch(edge -> edge.to() == i))
				.map(i -> children(tree, i).collect(Collectors.toSet()))
				.filter(n -> n.contains(u))
				.flatMap(Collection::stream)
				.distinct()
				.filter(n -> !n.equals(u))
				.reduce(u, (uu, w) -> select(uu, w, v));
	}

	private Stream<Integer> children(Tree tree, int i) {
		return Stream.concat(Stream.of(i), tree.edges().filter(edge -> edge.from() == i).flatMap(edge -> children(tree, edge.to())));
	}

	private int select(int u, int w, int v) {
		double uw = matrix.distance(u, w);
		double wu = matrix.distance(w, u);
		double uv = matrix.distance(u, v);
		double wv = matrix.distance(w, v);
		double a = contemporary(uw, uv, wv);
		double b = ancestor(wu, uv, wv);
		return (a >= b && mean(u) > mean(w)) || (a < b && uv > wv) ? w : u;
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
