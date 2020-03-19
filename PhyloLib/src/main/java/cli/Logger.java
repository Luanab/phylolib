package cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class Logger {

	private static final String TEMPLATE = "%s: %s...";
	private static final String INFO = "INFO";
	private static final String WARNING = "WARNING";
	private static final String ERROR = "ERROR";
	private static final String INFORMATION = "... For more information on how to use this application please refer to the usage message by running 'phylolib help'";
	private static final String USAGE = "src/main/resources/usage.txt";

	public static void info(String message, Object... parts) {
		print(INFO, message, parts);
	}

	public static void warning(String message, Object... parts) {
		print(WARNING, message, parts);
	}

	public static void error(String message, Object... parts) {
		print(ERROR, message + INFORMATION, parts);
	}

	private static void print(String type, String message, Object[] parts) {
		System.out.println(String.format(TEMPLATE, type, String.format(message, parts)));
	}

	public static void usage() throws IOException {
		try (Stream<String> usage = Files.lines(Paths.get(USAGE))) {
			usage.forEach(System.out::println);
		}
	}

}
