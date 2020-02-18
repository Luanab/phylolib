package exception;

public class NoCommandException extends ArgumentException {

	public NoCommandException() {
		super("No command has been specified");
	}

}
