package exception;

public class RepeatedCommandException extends ArgumentException {

	public RepeatedCommandException(String command) {
		super("Repeated command '" + command + "'");
	}

}
