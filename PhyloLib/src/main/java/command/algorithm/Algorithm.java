package command.algorithm;

import command.ICommand;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;

public abstract class Algorithm implements ICommand<Matrix, Tree> {

	@Override
	public final Tree process(Matrix matrix) {
		Tree tree = new Tree(matrix.ids());
		init(matrix);
		while (!isFinished(tree)) {
			Edge edge = select();
			join(edge);
			reduce(edge, tree);
		}
		return tree;
	}

	protected abstract void init(Matrix matrix);

	protected abstract boolean isFinished(Tree tree);

	protected abstract Edge select();

	protected abstract void join(Edge edge);

	protected abstract void reduce(Edge edge, Tree tree);

}
