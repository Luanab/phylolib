package exception;

/**
 * Represents an error derived from the definition of an unrepeatable command more than once in the command line arguments.
 */
public class RepeatedCommandException extends ArgumentException {

	public RepeatedCommandException(String command) {
		super("Repeated command '" + command + "'");
	}

}
