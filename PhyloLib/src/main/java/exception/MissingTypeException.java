package exception;

public class MissingTypeException extends Exception {

	public MissingTypeException(String command) {
		super("Missing type for command '" + command + "'");
	}

}
