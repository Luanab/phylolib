package exception;

public class MissingInputException extends ArgumentException {

	public MissingInputException(String input) {
		super("Missing " + input + " input");
	}

}
