package exception;

public class InvalidCommandException extends ArgumentException {

	public InvalidCommandException(String command) {
		super("Invalid command name '" + command + "'");
	}

}
