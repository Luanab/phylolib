package cli;

import command.algorithm.Algorithm;
import command.correction.Correction;
import command.distance.Distance;
import command.optimization.Optimization;
import reflection.Types;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;

/**
 * Enumerates the available commands with their respective repeatability and class.
 */
public enum Command {

	DISTANCE(false, Distance.class),
	CORRECTION(false, Correction.class),
	ALGORITHM(false, Algorithm.class),
	OPTIMIZATION(true, Optimization.class);

	private final boolean repeatable;
	private final Class<?> command;
	private Map<String, Constructor<?>> types;

	/**
	 * Creates a command corresponding to the given class.
	 *
	 * @param repeatable the indication of whether this command can be repeated or not
	 * @param command    the class corresponding to this command
	 */
	Command(boolean repeatable, Class<?> command) {
		this.repeatable = repeatable;
		this.command = command;
	}

	/**
	 * Gets the command corresponding to the given name.
	 *
	 * @param name the name of the command
	 *
	 * @return the corresponding Command object
	 */
	public static Command get(String name) {
		return Arrays.stream(values()).filter(c -> name.equalsIgnoreCase(c.name())).findFirst().orElse(null);
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	/**
	 * Checks whether or not this Command is repeatable.
	 *
	 * @return true if this command is repeatable, false otherwise
	 */
	public boolean isRepeatable() {
		return repeatable;
	}

	/**
	 * Gets the constructor of the specified type for this command.
	 *
	 * @param name the name of type for this command
	 *
	 * @return the constructor of the specified type for this command
	 */
	public Constructor<?> type(String name) {
		return (types == null ? types = Types.get(command) : types).get(name);
	}

}
