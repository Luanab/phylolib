package data;

import cli.Options;
import data.dataset.Dataset;
import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import data.tree.ITreeFormatter;
import data.tree.Tree;
import exception.InvalidFileException;
import exception.InvalidFormatException;
import exception.MissingInputException;

import java.io.IOException;

public final class Context {

	private Dataset dataset;
	private Matrix matrix;
	private Tree tree;

	public Dataset getDataset() {
		return dataset;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public Tree getTree() {
		return tree;
	}

	public void readDataset(Options options) throws InvalidFileException, InvalidFormatException, MissingInputException, IOException {
		dataset = IReader.read(options, "dataset", 'd', dataset, IDatasetFormatter.FORMATTERS);
	}

	public void readMatrix(Options options) throws InvalidFileException, InvalidFormatException, MissingInputException, IOException {
		matrix = IReader.read(options, "matrix", 'm', matrix, IMatrixFormatter.FORMATTERS);
	}

	public void readTree(Options options) throws InvalidFileException, InvalidFormatException, MissingInputException, IOException {
		tree = IReader.read(options, "tree", 't', tree, ITreeFormatter.FORMATTERS);
	}

	public void writeMatrix(Options options, Matrix value) throws InvalidFileException, IOException, InvalidFormatException {
		IWriter.write(options, matrix = value, IMatrixFormatter.FORMATTERS);
	}

	public void writeTree(Options options, Tree value) throws InvalidFileException, IOException, InvalidFormatException {
		IWriter.write(options, tree = value, ITreeFormatter.FORMATTERS);
	}

}
