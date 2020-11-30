package pt.ist.phylolib.exception;

/**
 * Represents an error derived from the definition of an invalid command in the command line arguments.
 */
public class InvalidCommandException extends ArgumentException {

	public InvalidCommandException(String command) {
		super("Invalid command name '" + command + "'");
	}

}
