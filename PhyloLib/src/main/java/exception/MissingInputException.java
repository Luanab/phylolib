package exception;

/**
 * Represents an error derived from the definition of no input for a command in the command line arguments.
 */
public class MissingInputException extends ArgumentException {

	public MissingInputException(String input) {
		super("Missing " + input + " input");
	}

}
