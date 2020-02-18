package exception;

public class InvalidOptionFormatException extends InvalidFormatException {

	public InvalidOptionFormatException(String value) {
		super("option", value);
	}

}
