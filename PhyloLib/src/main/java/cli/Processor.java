package cli;

import data.dataset.DatasetProcessor;
import data.matrix.MatrixProcessor;
import data.tree.TreeProcessor;
import reflection.Types;

import java.lang.reflect.Constructor;
import java.util.Map;

public enum Processor {

	DATASET(Option.DATASET, DatasetProcessor.class),
	MATRIX(Option.MATRIX, MatrixProcessor.class),
	TREE(Option.TREE, TreeProcessor.class);

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
		return (types == null ? types = Types.get("data." + toString(), processor) : types).get(type);
	}

}
