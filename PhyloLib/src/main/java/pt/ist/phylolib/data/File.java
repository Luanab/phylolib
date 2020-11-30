package pt.ist.phylolib.data;

import pt.ist.phylolib.cli.Data;
import pt.ist.phylolib.logging.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a file as a data type processor and a path.
 */
public class File {

	private static final String INVALID = "Ignored invalid %s file option '%s'";

	private final Object processor;
	private final Path path;

	/**
	 * Creates a file corresponding to the given data type processor and path.
	 *
	 * @param processor the data type processor
	 * @param path      the path of the file
	 */
	public File(Object processor, Path path) {
		this.processor = processor;
		this.path = path;
	}

	/**
	 * Gets a file corresponding to the given file option and data type.
	 * <p>
	 * Returns null and logs a message if the file option is invalid.
	 *
	 * @param file the file option to parse the data type and file path from
	 * @param data the data type of the file
	 *
	 * @return the file for the file option and data type
	 */
	public static File get(String file, Data data) {
		String[] values = file.split(":", 2);
		Constructor<?> type;
		if (values.length == 1 || values[0].isBlank() || values[1].isBlank() || (type = data.type(values[0])) == null) {
			Log.warning(INVALID, data, file);
			return null;
		}
		try {
			return new File(type.newInstance(), Paths.get(values[1]));
		} catch (InvalidPathException exception) {
			Log.warning(INVALID, data, file);
			return null;
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException exception) {
			Log.exception(exception);
			return null;
		}
	}

	public Object processor() {
		return processor;
	}

	public Path path() {
		return path;
	}

}
