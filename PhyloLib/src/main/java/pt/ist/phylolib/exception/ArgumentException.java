package pt.ist.phylolib.exception;

/**
 * Wraps all exceptions derived from command line arguments related issues.
 */
public class ArgumentException extends Exception {

	public ArgumentException(String message) {
		super(message);
	}

}
