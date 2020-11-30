package pt.ist.phylolib.data.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a phylogenetic tree as a set of profiles identified by their ids and the {@link Edge edges} that connect those profiles.
 */
public final class Tree {

	private final String[] ids;
	private final List<Edge> edges;

	/**
	 * Creates a phylogenetic tree corresponding to the given set of ids with no {@link Edge edges} connecting them.
	 *
	 * @param ids the ids of the profiles of this tree
	 */
	public Tree(String[] ids) {
		this.ids = ids;
		this.edges = new ArrayList<>();
	}

	/**
	 * Creates a phylogenetic tree corresponding to the given set of ids and {@link Edge edges}.
	 *
	 * @param ids   the ids of the profiles of this tree
	 * @param edges the edges connecting the profiles
	 */
	public Tree(String[] ids, List<Edge> edges) {
		this.ids = ids;
		this.edges = edges;
	}

	public String[] ids() {
		return ids;
	}

	public Stream<Edge> edges() {
		return edges.stream();
	}

	/**
	 * Adds a given {@link Edge edge} to this tree.
	 *
	 * @param edge the edge to add to this tree.
	 */
	public void add(Edge edge) {
		edges.add(edge);
	}

	/**
	 * Removes a given {@link Edge edge} from this tree.
	 *
	 * @param edge the edge to remove from this tree
	 */
	public void remove(Edge edge) {
		edges.remove(edge);
	}

}
