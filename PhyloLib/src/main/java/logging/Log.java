package logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class Log {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static final String USAGE = " - for more information on how to use this application please refer to the usage message by running 'phylolib help'";

	static {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
		} catch (SecurityException | IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	public static void info(String message, Object... parts) {
		LOGGER.info(String.format(message, parts));
	}

	public static void warning(String message, Object... parts) {
		LOGGER.warning(String.format(message, parts));
	}

	public static void error(String message, Object... parts) {
		LOGGER.severe(String.format(message + USAGE, parts));
	}

}
