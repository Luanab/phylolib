package command.algorithm;

import cli.Arguments;
import cli.Command;
import command.ICommand;
import command.algorithm.gcp.*;
import command.algorithm.mst.GoeBURST;
import command.algorithm.mst.GrapeTree;
import command.algorithm.nj.SaitouNei;
import command.algorithm.nj.StudierKeppler;
import command.algorithm.nj.UNJ;
import data.Context;
import data.matrix.Matrix;
import data.tree.ClusterSet;
import data.tree.Pair;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Algorithm implements ICommand<Matrix, Tree> {

	public static void run(Arguments arguments, Context context) throws MissingInputException {
		ICommand.run(arguments, context, Command.ALGORITHM, context::readMatrix, context::writeTree, new HashMap<>() {{
			put("goeburst", new GoeBURST());
			put("grapetree", new GrapeTree());
			put("sl", new SL());
			put("cl", new CL());
			put("upgma", new UPGMA());
			put("upgmc", new UPGMC());
			put("wpgma", new WPGMA());
			put("wpgmc", new WPGMC());
			put("saitounei", new SaitouNei());
			put("studierkeppler", new StudierKeppler());
			put("unj", new UNJ());
		}});
	}

	@Override
	public final Tree process(Matrix matrix) {
		ClusterSet clusterSet = new ClusterSet(matrix);
		Tree tree = new Tree();
		int u = matrix.size();
		while (clusterSet.clusters().count() > 1) {
			Pair<Integer, Integer> selected = select(matrix, clusterSet);
			int i = selected.getFirst();
			int j = selected.getSecond();
			join(clusterSet, i, j, u, tree);
			reduce(clusterSet, i, j, u++);
		}
		return tree;
	}

	protected final Pair<Integer, Integer> select(Matrix matrix, ClusterSet set) {
		Pair<Integer, Integer> selected = null;
		double min = Double.MAX_VALUE;
		List<Integer> elements = set.clusters().collect(Collectors.toList());
		for (int i : elements) {
			for (int j : elements) {
				double distance = dissimilarity(set, i, j);
				Pair<Integer, Integer> current = new Pair<>(i, i);
				if (distance < min || (distance == min && tiebreak(matrix, set, selected, current) > 0)) {
					min = distance;
					selected = current;
				}
			}
		}
		return selected;
	}

	protected abstract double dissimilarity(ClusterSet set, int i, int j);

	protected abstract int tiebreak(Matrix matrix, ClusterSet set, Pair<Integer, Integer> i, Pair<Integer, Integer> j);

	protected void join(ClusterSet set, int i, int j, int u, Tree tree) {
		set.remove(i);
		set.remove(j);
		double iu = branch(set, i, j, u);
		double ju = set.get(i, j) - iu;
		set.put(u, i, iu);
		set.put(u, j, ju);
		// TODO: add cluster to tree
	}

	protected abstract double branch(ClusterSet set, int i, int j, int u);

	protected void reduce(ClusterSet set, int i, int j, int u) {
		set.put(u, k -> dissimilarity(set, i, j, u, k));
	}

	protected abstract double dissimilarity(ClusterSet set, int i, int j, int u, int k);

}
