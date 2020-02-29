package data;

import cli.Format;
import cli.Logger;
import cli.Options;
import exception.InvalidFileException;
import exception.InvalidFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Optional;

@FunctionalInterface
public interface IWriter<T> {

	static <T> void write(Options options, T value, HashMap<String, ? extends IWriter<T>> map)
			throws InvalidFileException, IOException, InvalidFormatException {
		Optional<String> output = options.remove("out", 'o', Format.FILE);
		if (output.isPresent()) {
			File<? extends IWriter<T>> file = new File<>(output.get(), map);
			Files.write(file.getPath(), file.getFormatter().format(value).getBytes());
			Logger.info("Output written to '" + file.getPath().toString() + "'");
		}
	}

	String format(T data);

}
