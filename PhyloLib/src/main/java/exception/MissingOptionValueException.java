package exception;

public class MissingOptionValueException extends CommandLineException {

    public MissingOptionValueException(String type, String option) {
        super("Type '" + type + "' is missing value for option '" + option + "'");
    }

}
