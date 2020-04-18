package exception;

public class NoCommandException extends Exception {

	public NoCommandException() {
		super("No command has been specified");
	}

}
