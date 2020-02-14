package exception;

public class InvalidCommandException extends ArgumentException {

    public InvalidCommandException(String command) {
        super("Command '" + command + "' does not exist");
    }

}
