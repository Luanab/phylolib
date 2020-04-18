package data;

import cli.Options;
import data.dataset.Dataset;
import data.matrix.Matrix;
import data.tree.Tree;
import exception.MissingInputException;

public final class Context {

	private Dataset dataset;
	private Matrix matrix;
	private Tree tree;

	public Dataset getDataset(Options options) throws MissingInputException {
		return dataset = IReader.read(options, dataset, Processor.DATASET);
	}

	public Matrix getMatrix(Options options) throws MissingInputException {
		return matrix = IReader.read(options, matrix, Processor.MATRIX);
	}

	public Tree getTree(Options options) throws MissingInputException {
		return tree = IReader.read(options, tree, Processor.TREE);
	}

	public void setMatrix(Options options, Matrix value) {
		IWriter.write(options, matrix = value, Processor.MATRIX);
	}

	public void setTree(Options options, Tree value) {
		IWriter.write(options, tree = value, Processor.TREE);
	}

}
