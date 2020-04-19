package exception;

public class MissingTypeException extends ArgumentException {

	public MissingTypeException(String command) {
		super("Missing type for command '" + command + "'");
	}

}
