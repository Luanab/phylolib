package data;

import cli.Format;
import cli.Logger;
import cli.Option;
import cli.Options;
import data.dataset.Dataset;
import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.matrix.Matrix;
import data.tree.ITreeFormatter;
import data.tree.Tree;
import exception.MissingInputException;

import java.util.HashMap;
import java.util.Optional;

public final class Context {

	private static final String OVERWRITING = "Overwriting context value";

	private Dataset dataset;
	private Matrix matrix;
	private Tree tree;

	private static <T, R extends T> Optional<File<R>> getFile(Options options, Option option, T previous, HashMap<String, R> map) {
		Optional<String> data = options.remove(option, Format.FILE);
		if (data.isPresent()) {
			Optional<File<R>> file = File.get(data.get(), map);
			if (file.isPresent() && previous != null)
				Logger.info(OVERWRITING);
			return file;
		}
		return Optional.empty();
	}

	private static <T> T read(Options options, Option option, T previous, HashMap<String, ? extends IReader<T>> map) throws MissingInputException {
		return getFile(options, option, previous, map)
				.map(file -> file.getFormatter().read(file.getPath()))
				.or(() -> Optional.ofNullable(previous))
				.orElseThrow(() -> new MissingInputException(option.getKey()));
	}

	private static <T> void write(Options options, T previous, T value, HashMap<String, ? extends IWriter<T>> map) {
		getFile(options, Option.OUT, previous, map).ifPresent(file -> file.getFormatter().write(file.getPath(), value));
	}

	public Dataset readDataset(Options options) throws MissingInputException {
		return dataset = read(options, Option.DATASET, dataset, IDatasetFormatter.FORMATTERS);
	}

	public Matrix readMatrix(Options options) throws MissingInputException {
		return matrix = read(options, Option.MATRIX, matrix, IMatrixFormatter.FORMATTERS);
	}

	public Tree readTree(Options options) throws MissingInputException {
		return tree = read(options, Option.TREE, tree, ITreeFormatter.FORMATTERS);
	}

	public void writeMatrix(Options options, Matrix value) {
		write(options, matrix, matrix = value, IMatrixFormatter.FORMATTERS);
	}

	public void writeTree(Options options, Tree value) {
		write(options, tree, tree = value, ITreeFormatter.FORMATTERS);
	}

}
