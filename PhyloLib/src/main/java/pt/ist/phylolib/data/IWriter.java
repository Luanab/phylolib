package pt.ist.phylolib.data;

import pt.ist.phylolib.cli.Data;
import pt.ist.phylolib.cli.Option;
import pt.ist.phylolib.cli.Options;
import pt.ist.phylolib.logging.Log;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for the writing of data into files.
 *
 * @param <T> the type of the data to write
 */
@FunctionalInterface
public interface IWriter<T> {

	String WRITE = "%s writing file '%s'";
	String STARTED = "Started";
	String FINISHED = "Finished";
	String FAILED = "Failed";

	/**
	 * Writes a value from an option of the given data type in the given options.
	 * <p>
	 * If there is no option of that data type, then returns.
	 *
	 * @param options the options to search for the out option
	 * @param value   the value to write
	 * @param data    the data type to write
	 * @param <T>     the output data type
	 */
	@SuppressWarnings("unchecked")
	static <T> void write(Options options, T value, Data data) {
		String output = options.remove(Option.OUT);
		if (output != null) {
			File file = File.get(output, data);
			if (file != null) {
				Path path = file.path();
				Log.info(WRITE, STARTED, path);
				try {
					Files.write(path, (value == null ? "" : ((IWriter<T>) file.processor()).parse(value)).getBytes());
					Log.info(WRITE, FINISHED, path);
				} catch (Exception exception) {
					Log.warning(WRITE, FAILED, path);
				}
			}
		}
	}

	/**
	 * Parses the output data into a String.
	 *
	 * @param data the output data to parse
	 *
	 * @return the output data parsed into a String
	 */
	String parse(T data);

}
