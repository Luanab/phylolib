package pt.ist.phylolib.exception;

/**
 * Represents an error derived from the definition of no command in the command line arguments.
 */
public class NoCommandException extends ArgumentException {

	public NoCommandException() {
		super("No command has been specified");
	}

}
