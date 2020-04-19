package exception;

public class InvalidTypeException extends ArgumentException {

	public InvalidTypeException(String command, String type) {
		super("Invalid type '" + type + "' for command '" + command + "'");
	}

}
