package exception;

public class InvalidFormatException extends CommandLineException {

    public InvalidFormatException(String format) {
        super("Invalid format '" + format + "'");
    }

}
