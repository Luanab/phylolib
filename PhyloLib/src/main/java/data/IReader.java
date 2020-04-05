package data;

import cli.Option;
import cli.Options;
import exception.MissingInputException;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReader<T> extends IProcessor {

	String READ = "Read";

	static <T> T read(Options options, Option option, T previous, HashMap<String, ? extends IReader<T>> map) throws MissingInputException {
		Holder<T> result = new Holder<>();
		IProcessor.process(options, option, map, READ, file -> {
			try (Stream<String> data = Files.lines(file.getPath())) {
				result.value = file.getProcessor().parse(data);
			}
		});
		if (result.value != null)
			return result.value;
		if (previous == null)
			throw new MissingInputException(option.getKey());
		return previous;
	}

	T parse(Stream<String> data);

	class Holder<T> {

		T value;

	}

}
