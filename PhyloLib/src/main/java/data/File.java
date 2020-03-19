package data;

import cli.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;

public final class File<T> {

	private static final String SEPARATOR = ":";
	private static final String INVALID = "Ignoring invalid %s '%s'";
	private static final String FILE = "file";
	private static final String FORMAT = "format";
	private static final String PATH = "path";

	private final T formatter;
	private final Path path;

	public File(T formatter, Path path) {
		this.formatter = formatter;
		this.path = path;
	}

	public static <T> Optional<File<T>> get(String file, HashMap<String, ? extends T> map) {
		String[] values = file.split(SEPARATOR, 2);
		if (values.length == 1 || values[0].isBlank() || values[1].isBlank()) {
			Logger.warning(INVALID, FILE, file);
			return Optional.empty();
		}
		String format = values[0], path = values[1];
		if (!map.containsKey(format)) {
			Logger.warning(INVALID, FORMAT, format);
			return Optional.empty();
		}
		try {
			return Optional.of(new File<>(map.get(format), Paths.get(path)));
		} catch (InvalidPathException e) {
			Logger.warning(INVALID, PATH, path);
			return Optional.empty();
		}
	}

	public T getFormatter() {
		return formatter;
	}

	public Path getPath() {
		return path;
	}

}
