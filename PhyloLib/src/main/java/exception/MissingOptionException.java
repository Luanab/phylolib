package exception;

public class MissingOptionException extends ArgumentException {

    public MissingOptionException(String command, String option) {
        super("Command '" + command + "' is missing option '" + option + "'");
    }

}
