package exception;

public abstract class ArgumentException extends Exception {

	ArgumentException(String message) {
		super("Error: " + message + "... For more information on how to use this application " +
			  "please refer to the usage message by running either phylolib -h or phylolib --help...");
	}

}
