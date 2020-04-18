package data;

import cli.Options;
import exception.MissingInputException;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReader<T> {

	String READING = "Read operation on file '%s' %s";
	String STARTED = "started";
	String SUCCEEDED = "succeeded";
	String FAILED = "failed";

	static <T> T read(Options options, T previous, Processor processor) throws MissingInputException {
		Optional<String> input = options.remove(processor.getOption());
		if (input.isPresent()) {
			Optional<File> file = File.get(input.get(), processor);
			if (file.isPresent()) {
				Path path = file.get().getPath();
				Log.info(READING, path, STARTED);
				try (Stream<String> data = Files.lines(path)) {
					T result = ((IReader<T>) file.get().getProcessor()).parse(data);
					Log.info(READING, path, SUCCEEDED);
					return result;
				} catch (Exception e) {
					Log.warning(READING, path, FAILED);
				}
			}
		}
		if (previous == null)
			throw new MissingInputException(processor.getName());
		return previous;
	}

	T parse(Stream<String> data);

}
