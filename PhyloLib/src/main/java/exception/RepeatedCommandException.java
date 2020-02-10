package exception;

public class RepeatedCommandException extends CommandLineException {

    public RepeatedCommandException(String command) {
        super("Command '" + command + "' can only be defined once");
    }

}
