package data;

import cli.Option;
import cli.Options;
import logging.Log;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Optional;

public interface IProcessor {

	String PROCESSING = "%s operation on file '%s' %s";
	String STARTED = "started";
	String SUCCEEDED = "succeeded";
	String FAILED = "failed";

	static <T> void process(Options options, Option option, HashMap<String, T> map, String process, Operation<T> operation) {
		Optional<String> data = options.remove(option);
		if (data.isPresent()) {
			Optional<File<T>> file = File.get(data.get(), map);
			if (file.isPresent()) {
				Path path = file.get().getPath();
				Log.info(PROCESSING, process, path, STARTED);
				try {
					operation.process(file.get());
					Log.info(PROCESSING, process, path, SUCCEEDED);
				} catch (Exception e) {
					Log.warning(PROCESSING, process, path, FAILED);
				}
			}
		}
	}

	interface Operation<T> {

		void process(File<T> file) throws IOException;

	}

}
