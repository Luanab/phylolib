package exception;

public abstract class ArgumentException extends Exception {

	ArgumentException(String message) {
		super("Error: " + message + "...");
	}

}
