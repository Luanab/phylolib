package exception;

public class InvalidTypeException extends Exception {

	public InvalidTypeException(String command, String type) {
		super("Invalid type '" + type + "' for command '" + command + "'");
	}

}
