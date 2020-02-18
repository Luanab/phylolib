package exception;

public class InvalidTypeException extends ArgumentException {

	public InvalidTypeException(String command, String type) {
		super("Command '" + command + "' does not support type '" + type + "'");
	}

}
