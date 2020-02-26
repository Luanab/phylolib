package exception;

public class MissingInputException extends ArgumentException {

	public MissingInputException(String type) {
		super("Missing input for '" + type + "'");
	}

}
