package command.algorithm;

import cli.Arguments;
import command.Command;
import command.ICommand;
import command.algorithm.bt.gcp.*;
import command.algorithm.bt.nj.SaitouNei;
import command.algorithm.bt.nj.StudierKeppler;
import command.algorithm.bt.nj.UNJ;
import command.algorithm.mst.GoeBURST;
import command.algorithm.mst.GrapeTree;
import data.Context;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.HashMap;

public abstract class Algorithm implements ICommand<Matrix, Tree> {

	public static void run(Arguments arguments, Context context) throws MissingInputException {
		ICommand.run(arguments, context, Command.ALGORITHM, context::getMatrix, context::setTree, new HashMap<>() {{
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
		Tree tree = new Tree();
		while (distinct() > 1) {
			Edge edge = select();
			join(edge);
			reduce(edge, tree);
		}
		return tree;
	}

	protected abstract long distinct();

	protected abstract Edge select();

	protected abstract void join(Edge edge);

	protected abstract void reduce(Edge edge, Tree tree);

}
