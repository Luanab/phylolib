package data;

import cli.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface IWriter<T> {

	String WRITING = "Writing to '%s'";
	String SUCCEEDED = " succeeded";
	String FAILED = " failed";

	default void write(Path path, T data) {
		String file = path.toString();
		Logger.info(WRITING, file);
		try {
			Files.write(path, format(data).getBytes());
			Logger.info(WRITING + SUCCEEDED, file);
		} catch (IOException e) {
			Logger.warning(WRITING + FAILED, file);
		}
	}

	String format(T data);

}
