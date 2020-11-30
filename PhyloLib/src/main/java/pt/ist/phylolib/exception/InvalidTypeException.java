package pt.ist.phylolib.exception;

/**
 * Represents an error derived from the definition of an invalid command type in the command line arguments.
 */
public class InvalidTypeException extends ArgumentException {

	public InvalidTypeException(String command, String type) {
		super("Invalid type '" + type + "' for command '" + command + "'");
	}

}
