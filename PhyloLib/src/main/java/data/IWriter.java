package data;

import cli.Option;
import cli.Options;

import java.nio.file.Files;
import java.util.HashMap;

@FunctionalInterface
public interface IWriter<T> extends IProcessor {

	String WRITE = "Write";

	static <T> void write(Options options, T value, HashMap<String, ? extends IWriter<T>> map) {
		IProcessor.process(options, Option.OUT, map, WRITE, file -> Files.write(file.getPath(), (value == null ? "" : file.getProcessor().format(value)).getBytes()));
	}

	String format(T data);

}
