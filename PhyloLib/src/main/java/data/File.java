package data;

import exception.InvalidFileException;
import exception.InvalidFormatException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public final class File<T> {

	private final T formatter;
	private final Path path;

	public File(String file, HashMap<String, T> map) throws InvalidFileException, InvalidFormatException {
		String[] values = file.split(":", 2);
		if (values.length == 1 || values[0].isBlank() || values[1].isBlank())
			throw new InvalidFileException(file);
		String format = values[0], path = values[1];
		if (!map.containsKey(format))
			throw new InvalidFormatException(format);
		this.formatter = map.get(format);
		this.path = Paths.get(path);
	}

	public T getFormatter() {
		return formatter;
	}

	public Path getPath() {
		return path;
	}

}
