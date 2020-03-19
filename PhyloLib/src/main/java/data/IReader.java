package data;

import cli.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReader<T> {

	String READING = "Reading from '%s'";
	String SUCCEEDED = " succeeded";
	String FAILED = " failed";

	default T read(Path path) {
		String file = path.toString();
		Logger.info(READING, file);
		try (Stream<String> data = Files.lines(path)) {
			T result = parse(data);
			Logger.info(READING + SUCCEEDED, file);
			return result;
		} catch (IOException e) {
			Logger.warning(READING + FAILED, file);
			return null;
		}
	}

	T parse(Stream<String> data);

}
