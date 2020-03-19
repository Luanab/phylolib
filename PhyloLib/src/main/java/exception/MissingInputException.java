package exception;

public class MissingInputException extends Exception {

	public MissingInputException(String input) {
		super("Missing input for '" + input + "'");
	}

}
