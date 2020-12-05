package pt.ist.phylolib.command.optimization;

import pt.ist.phylolib.cli.Options;
import pt.ist.phylolib.command.ICommand;
import pt.ist.phylolib.data.Context;
import pt.ist.phylolib.data.dataset.Dataset;
import pt.ist.phylolib.data.matrix.Matrix;
import pt.ist.phylolib.data.tree.Edge;
import pt.ist.phylolib.data.tree.Tree;
import pt.ist.phylolib.exception.MissingInputException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Responsible for optimizing a {@link Tree phylogenetic tree} into another.
 */
public abstract class Optimization implements ICommand<Tree, Tree> {

	private int loci;
	private String[] ids;
	private Matrix matrix;
	private PriorityQueue<Edge> edges;

	@Override
	public void init(Context context, Options options) throws MissingInputException {
		Dataset dataset = context.getDataset(options);
		this.loci = dataset.profile(0).size();
		this.ids = dataset.ids();
		this.matrix = context.getMatrix(options);
		this.edges = new PriorityQueue<>(Comparator.comparingDouble(Edge::distance));
	}

	@Override
	public final Tree process(Tree tree) {
		Tree result = new Tree(ids);
		Map<Integer, Integer> map = new HashMap<>(ids.length);
		for (int i = 0; i < tree.ids().length; i++) {
			String id = tree.ids()[i];
			map.put(i, IntStream.range(0, ids.length).filter(j -> ids[j].equals(id)).findFirst().orElseThrow());
		}
		tree.edges().map(e -> new Edge(map.get(e.from()), map.get(e.to()), e.distance())).peek(result::add).forEach(edges::add);
		while (!edges.isEmpty()) {
			Edge previous = edges.peek();
			result.remove(previous);
			Edge current = join(loci, matrix, result, previous);
			edges.remove();
			result.add(current);
			if (current.distance() > previous.distance())
				edges.add(current);
		}
		return result;
	}

	/**
	 * Gets an edge that connects the two clusters of a given tree.
	 *
	 * @param loci   the number of loci of each profile
	 * @param matrix the distance matrix with the distances between each profile
	 * @param tree   the phylogenetic tree with two clusters
	 * @param edge   the previous edge that connected the tree
	 *
	 * @return an edge that connects the tree
	 */
	protected abstract Edge join(int loci, Matrix matrix, Tree tree, Edge edge);

}
