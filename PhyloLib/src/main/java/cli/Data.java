package cli;

import data.dataset.DatasetParser;
import data.matrix.MatrixParser;
import data.tree.TreeParser;
import reflection.Types;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * Enumerates the available data types with their respective option and class.
 */
public enum Data {

	DATASET(Option.DATASET, DatasetParser.class),
	MATRIX(Option.MATRIX, MatrixParser.class),
	TREE(Option.TREE, TreeParser.class);

	private final Option option;
	private final Class<?> processor;
	private Map<String, Constructor<?>> types;

	/**
	 * Creates a data type corresponding to the given option and class.
	 *
	 * @param option    the option corresponding to this data type
	 * @param processor the class corresponding to this data type
	 */
	Data(Option option, Class<?> processor) {
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

	/**
	 * Gets the constructor of the specified processor type for this data type.
	 *
	 * @param name the name of the processor type for this data type
	 *
	 * @return the constructor of the specified processor type for this data type
	 */
	public Constructor<?> type(String name) {
		return (types == null ? types = Types.get(processor) : types).get(name);
	}

}
