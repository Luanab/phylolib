package data;

import cli.Option;
import cli.Options;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@FunctionalInterface
public interface IWriter<T> {

	String WRITING = "Write operation on file '%s' %s";
	String STARTED = "started";
	String SUCCEEDED = "succeeded";
	String FAILED = "failed";

	static <T> void write(Options options, T value, Processor processor) {
		Optional<String> output = options.remove(Option.OUT);
		if (output.isPresent()) {
			Optional<File> file = File.get(output.get(), processor);
			if (file.isPresent()) {
				Path path = file.get().getPath();
				Log.info(WRITING, path, STARTED);
				try {
					Files.write(path, (value == null ? "" : ((IWriter<T>) file.get().getProcessor()).format(value)).getBytes());
					Log.info(WRITING, path, SUCCEEDED);
				} catch (Exception e) {
					Log.warning(WRITING, path, FAILED);
				}
			}
		}
	}

	String format(T data);

}
