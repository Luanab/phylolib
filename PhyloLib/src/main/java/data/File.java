package data;

import logging.Log;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

public class File<T> {

	private static final String SEPARATOR = ":";
	private static final String INVALID = "Ignoring invalid %s '%s'";
	private static final String FILE = "file";
	private static final String FORMAT = "format";
	private static final String PATH = "path";

	private final T processor;
	private final Path path;

	public File(T processor, Path path) {
		this.processor = processor;
		this.path = path;
	}

	public static <T> Optional<File<T>> get(String file, HashMap<String, T> map) {
		String[] values = file.split(SEPARATOR, 2);
		if (values.length == 1 || values[0].isBlank() || values[1].isBlank()) {
			Log.warning(INVALID, FILE, file);
			return Optional.empty();
		}
		String format = values[0], path = values[1];
		if (!map.containsKey(format)) {
			Log.warning(INVALID, FORMAT, format);
			return Optional.empty();
		}
		try {
			return Optional.of(new File<>(map.get(format), Paths.get(path)));
		} catch (InvalidPathException e) {
			Log.warning(INVALID, PATH, path);
			return Optional.empty();
		}
	}

	public T getProcessor() {
		return processor;
	}

	public Path getPath() {
		return path;
	}

}
