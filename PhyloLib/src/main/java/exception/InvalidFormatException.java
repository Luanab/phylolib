package exception;

public abstract class InvalidFormatException extends ArgumentException {

	public InvalidFormatException(String type, String value) {
		super("Invalid " + type + " format '" + value + "'");
	}

}
