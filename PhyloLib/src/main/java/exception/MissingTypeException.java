package exception;

public class MissingTypeException extends ArgumentException {

	public MissingTypeException(String command) {
		super("Command '" + command + "' is missing type");
	}

}
