package data;

import cli.Option;
import cli.Options;
import data.dataset.Dataset;
import data.dataset.IDatasetProcessor;
import data.matrix.IMatrixProcessor;
import data.matrix.Matrix;
import data.tree.ITreeProcessor;
import data.tree.Tree;
import exception.MissingInputException;

public final class Context {

	private Dataset dataset;
	private Matrix matrix;
	private Tree tree;

	public Dataset getDataset(Options options) throws MissingInputException {
		return dataset = IReader.read(options, Option.DATASET, dataset, IDatasetProcessor.PROCESSORS);
	}

	public Matrix getMatrix(Options options) throws MissingInputException {
		return matrix = IReader.read(options, Option.MATRIX, matrix, IMatrixProcessor.PROCESSORS);
	}

	public Tree getTree(Options options) throws MissingInputException {
		return tree = IReader.read(options, Option.TREE, tree, ITreeProcessor.PROCESSORS);
	}

	public void setMatrix(Options options, Matrix value) {
		IWriter.write(options, matrix = value, IMatrixProcessor.PROCESSORS);
	}

	public void setTree(Options options, Tree value) {
		IWriter.write(options, tree = value, ITreeProcessor.PROCESSORS);
	}

}
