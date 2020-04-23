package data;

import cli.Processor;
import logging.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File {

	private static final String INVALID = "Ignored invalid %s file option '%s'";

	private final Object processor;
	private final Path path;

	public File(Object processor, Path path) {
		this.processor = processor;
		this.path = path;
	}

	public static File get(String file, Processor processor) {
		String[] values = file.split(":", 2);
		Constructor<?> type;
		if (values.length == 1 || values[0].isBlank() || values[1].isBlank() || (type = processor.type(values[0])) == null) {
			Log.warning(INVALID, processor, file);
			return null;
		}
		try {
			return new File(type.newInstance(), Paths.get(values[1]));
		} catch (InvalidPathException exception) {
			Log.warning(INVALID, processor, file);
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
