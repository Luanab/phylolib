package exception;

public class MissingOptionException extends CommandLineException {

    public MissingOptionException(String command, String option) {
        super("Command '" + command + "' is missing option '" + option + "'");
    }

}
