package logging;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public final class Log {

	private static final Logger LOGGER = Logger.getGlobal();

	static {
		try {
			LogManager.getLogManager().readConfiguration(Log.class.getClassLoader().getResourceAsStream("logging.properties"));
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
		LOGGER.severe(String.format(message, parts));
	}

	public static void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
		LOGGER.throwing(sourceClass, sourceMethod, thrown);
	}

}
