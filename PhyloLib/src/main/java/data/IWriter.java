package data;

import cli.Option;
import cli.Options;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@FunctionalInterface
public interface IWriter<T> {

	String WRITE = "%s writing file '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String FAILED = "Failed";

	static <T> void write(Options options, T value, Processor processor) {
		Optional<String> output = options.remove(Option.OUT);
		if (output.isPresent()) {
			Optional<File> file = File.get(output.get(), processor);
			if (file.isPresent()) {
				Path path = file.get().getPath();
				Log.info(WRITE, STARTED, path);
				try {
					Files.write(path, (value == null ? "" : ((IWriter<T>) file.get().getProcessor()).format(value)).getBytes());
					Log.info(WRITE, FINISHED, path);
				} catch (Exception exception) {
					Log.warning(WRITE, FAILED, path);
				}
			}
		}
	}

	String format(T data);

}
