package pt.ist.phylolib.logging;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Responsible for the logging of information in the program.
 */
public final class Log {

	private static final Logger LOGGER = Logger.getGlobal();

	static {
		try {
			LogManager.getLogManager().readConfiguration(Log.class.getClassLoader().getResourceAsStream("logging.properties"));
		} catch (SecurityException | IOException exception) {
			LOGGER.severe(exception.getMessage());
		}
	}

	/**
	 * Logs the given info message.
	 *
	 * @param message the message to log
	 * @param parts   the parts that compose the message
	 */
	public static void info(String message, Object... parts) {
		LOGGER.info(String.format(message, parts));
	}

	/**
	 * Logs the given warning message.
	 *
	 * @param message the message to log
	 * @param parts   the parts that compose the message
	 */
	public static void warning(String message, Object... parts) {
		LOGGER.warning(String.format(message, parts));
	}

	/**
	 * Logs the given error message.
	 *
	 * @param message the message to log
	 * @param parts   the parts that compose the message
	 */
	public static void error(String message, Object... parts) {
		LOGGER.severe(String.format(message, parts));
	}

	/**
	 * Logs the given exception.
	 *
	 * @param exception the exception to log
	 */
	public static void exception(Exception exception) {
		LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
	}

}
