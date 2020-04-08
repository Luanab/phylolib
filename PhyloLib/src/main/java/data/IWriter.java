package data;

import cli.Option;
import cli.Options;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

@FunctionalInterface
public interface IWriter<T> {

	String WRITING = "Writing to file '%s' %s";
	String STARTED = "started";
	String SUCCEEDED = "succeeded";
	String FAILED = "failed";

	static <T, R extends IWriter<T>> void write(Options options, T value, Map<String, R> map) {
		Optional<String> output = options.remove(Option.OUT);
		if (output.isPresent()) {
			Optional<File<R>> file = File.get(output.get(), map);
			if (file.isPresent()) {
				Path path = file.get().getPath();
				Log.info(WRITING, path, STARTED);
				try {
					Files.write(path, (value == null ? "" : file.get().getProcessor().format(value)).getBytes());
					Log.info(WRITING, path, SUCCEEDED);
				} catch (Exception e) {
					Log.warning(WRITING, path, FAILED);
				}
			}
		}
	}

	String format(T data);

}
