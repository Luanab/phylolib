package pt.ist.phylolib.exception;

/**
 * Represents an error derived from the definition of no type for a command in the command line arguments.
 */
public class MissingTypeException extends ArgumentException {

	public MissingTypeException(String command) {
		super("Missing type for command '" + command + "'");
	}

}
