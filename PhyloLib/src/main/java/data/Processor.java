package data;

import cli.Option;
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
	private final Map<String, Constructor<?>> types;

	Processor(Option option, Class<?> type) {
		this.option = option;
		this.types = Reflection.types("data." + getName(), type);
	}

	public static Processor get(String command) {
		return valueOf(command.toUpperCase());
	}

	public String getName() {
		return name().toLowerCase();
	}

	public Option getOption() {
		return option;
	}

	public boolean hasType(String type) {
		return types.containsKey(type);
	}

	public Constructor<?> getType(String type) {
		return types.get(type);
	}

}
