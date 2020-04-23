package data;

import cli.Options;
import cli.Processor;
import exception.MissingInputException;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReader<T> {

	String READ = "%s reading file '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String FAILED = "Failed";

	@SuppressWarnings("unchecked")
	static <T> T read(Options options, T previous, Processor processor) throws MissingInputException {
		String input = options.remove(processor.option());
		if (input != null) {
			File file = File.get(input, processor);
			if (file != null) {
				Path path = file.path();
				Log.info(READ, STARTED, path);
				try (Stream<String> data = Files.lines(path)) {
					T result = ((IReader<T>) file.processor()).parse(data);
					Log.info(READ, FINISHED, path);
					return result;
				} catch (Exception exception) {
					Log.warning(READ, FAILED, path);
				}
			}
		}
		if (previous == null)
			throw new MissingInputException(processor.toString());
		return previous;
	}

	T parse(Stream<String> data);

}
