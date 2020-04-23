package cli;

import data.dataset.IDatasetProcessor;
import data.matrix.IMatrixProcessor;
import data.tree.ITreeProcessor;
import reflection.Reflection;

import java.lang.reflect.Constructor;
import java.util.Map;

public enum Processor {

	DATASET(Option.DATASET, IDatasetProcessor.class),
	MATRIX(Option.MATRIX, IMatrixProcessor.class),
	TREE(Option.TREE, ITreeProcessor.class);

	private final Option option;
	private final Class<?> processor;
	private Map<String, Constructor<?>> types;

	Processor(Option option, Class<?> processor) {
		this.option = option;
		this.processor = processor;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	public Option option() {
		return option;
	}

	public Constructor<?> type(String type) {
		return (types == null ? types = Reflection.types("data." + toString(), processor) : types).get(type);
	}

}
