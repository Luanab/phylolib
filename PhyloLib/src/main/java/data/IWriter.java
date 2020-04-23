package data;

import cli.Option;
import cli.Options;
import cli.Processor;
import logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;

@FunctionalInterface
public interface IWriter<T> {

	String WRITE = "%s writing file '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String FAILED = "Failed";

	@SuppressWarnings("unchecked")
	static <T> void write(Options options, T value, Processor processor) {
		String output = options.remove(Option.OUT);
		if (output != null) {
			File file = File.get(output, processor);
			if (file != null) {
				Path path = file.path();
				Log.info(WRITE, STARTED, path);
				try {
					Files.write(path, (value == null ? "" : ((IWriter<T>) file.processor()).format(value)).getBytes());
					Log.info(WRITE, FINISHED, path);
				} catch (Exception exception) {
					Log.warning(WRITE, FAILED, path);
				}
			}
		}
	}

	String format(T data);

}
