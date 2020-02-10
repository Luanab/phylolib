package exception;

public abstract class CommandLineException extends Exception {

    CommandLineException(String message) {
        super("Error: " + message + "...");
    }

}
