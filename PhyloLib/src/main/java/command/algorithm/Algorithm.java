package command.algorithm;

import command.ICommand;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

public abstract class Algorithm implements ICommand<Matrix, Tree> {

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
