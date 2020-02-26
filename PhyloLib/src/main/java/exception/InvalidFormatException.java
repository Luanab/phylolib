package exception;

public class InvalidFormatException extends ArgumentException {

	public InvalidFormatException(String value) {
		super("Invalid format '" + value + "'");
	}

}
