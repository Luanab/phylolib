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

	String READ = "%s reading file '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String FAILED = "Failed";

	@SuppressWarnings("unchecked")
	static <T> T read(Options options, T previous, Processor processor) throws MissingInputException {
		Optional<String> input = options.remove(processor.getOption());
		if (input.isPresent()) {
			Optional<File> file = File.get(input.get(), processor);
			if (file.isPresent()) {
				Path path = file.get().getPath();
				Log.info(READ, STARTED, path);
				try (Stream<String> data = Files.lines(path)) {
					T result = ((IReader<T>) file.get().getProcessor()).parse(data);
					Log.info(READ, FINISHED, path);
					return result;
				} catch (Exception exception) {
					Log.warning(READ, FAILED, path);
				}
			}
		}
		if (previous == null)
			throw new MissingInputException(processor.getName());
		return previous;
	}

	T parse(Stream<String> data);

}
