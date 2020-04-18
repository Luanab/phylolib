package exception;

public class RepeatedCommandException extends Exception {

	public RepeatedCommandException(String command) {
		super("Repeated command '" + command + "'");
	}

}
