package data;

import cli.Data;
import cli.Options;
import data.dataset.Dataset;
import data.matrix.Matrix;
import data.tree.Tree;
import exception.MissingInputException;

/**
 * Represents the context of the program with the three different data types used.
 */
public final class Context {

	private Dataset dataset;
	private Matrix matrix;
	private Tree tree;

	/**
	 * Returns and updates the dataset with the given options.
	 * <p>
	 * If there is no dataset option, then returns the previous value for the dataset.
	 * <p>
	 * If the previous value is null, then throws a MissingInputException.
	 *
	 * @param options the options to search for the dataset option
	 *
	 * @return the dataset read from the options or the previous value
	 *
	 * @throws MissingInputException if the dataset option is not provided and the previous value is null
	 */
	public Dataset getDataset(Options options) throws MissingInputException {
		return dataset = IReader.read(options, dataset, Data.DATASET);
	}

	/**
	 * Returns and updates the matrix with the given options.
	 * <p>
	 * If there is no matrix option, then returns the previous value for the matrix.
	 * <p>
	 * If the previous value is null, then throws a MissingInputException.
	 *
	 * @param options the options to search for the matrix option
	 *
	 * @return the matrix read from the options or the previous value
	 *
	 * @throws MissingInputException if the matrix option is not provided and the previous value is null
	 */
	public Matrix getMatrix(Options options) throws MissingInputException {
		return matrix = IReader.read(options, matrix, Data.MATRIX);
	}

	/**
	 * Reads and updates the tree with the given options.
	 * <p>
	 * If there is no tree option, then returns the previous value for the tree.
	 * <p>
	 * If the previous value is null, then throws a MissingInputException.
	 *
	 * @param options the options to search for the tree option
	 *
	 * @return the tree read from the options or the previous value
	 *
	 * @throws MissingInputException if the tree option is not provided and the previous value is null
	 */
	public Tree getTree(Options options) throws MissingInputException {
		return tree = IReader.read(options, tree, Data.TREE);
	}

	/**
	 * Writes and updates the matrix with the given options.
	 *
	 * @param options the options to search for the out option
	 * @param value   the matrix to write and update
	 */
	public void setMatrix(Options options, Matrix value) {
		IWriter.write(options, matrix = value, Data.MATRIX);
	}

	/**
	 * Writes and updates the tree with the given options.
	 *
	 * @param options the options to search for the out option
	 * @param value   the tree to write and update
	 */
	public void setTree(Options options, Tree value) {
		IWriter.write(options, tree = value, Data.TREE);
	}

}
