package cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public final class Logger {

	private static final String FORMAT = "%s: %s...";

	private static void print(String type, String message) {
		System.out.println(String.format(FORMAT, type, message));
	}

	public static void info(String message) {
		print("Info", message);
	}

	public static void warning(String message) {
		print("Warning", message);
	}

	public static void error(String message, boolean usage) {
		print("Error", !usage ? message : message + "... For more information on how to use this application " +
										  "please refer to the usage message by running either phylolib -h or phylolib --help");
	}

	public static void usage() throws IOException {
		try (Stream<String> usage = Files.lines(Paths.get("src/main/resources/usage.txt"))) {
			usage.forEach(System.out::println);
		}
	}

}
