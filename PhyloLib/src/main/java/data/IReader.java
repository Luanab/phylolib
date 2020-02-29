package data;

import cli.Format;
import cli.Logger;
import cli.Options;
import exception.InvalidFileException;
import exception.InvalidFormatException;
import exception.MissingInputException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReader<T> {

	static <T> T read(Options options, String option, char alias, T value, HashMap<String, ? extends IReader<T>> map)
			throws InvalidFileException, InvalidFormatException, MissingInputException, IOException {
		Optional<String> input = options.remove(option, alias, Format.FILE);
		if (input.isPresent()) {
			if (value != null)
				Logger.info("Overwriting '" + option + "' context value with parameter value");
			File<? extends IReader<T>> file = new File<>(input.get(), map);
			Logger.info("Reading input from '" + file.getPath().toString() + "'");
			try (Stream<String> data = Files.lines(file.getPath())) {
				return file.getFormatter().parse(data);
			}
		} else if (value == null)
			throw new MissingInputException(option);
		return value;
	}

	T parse(Stream<String> data);

}
