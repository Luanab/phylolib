package exception;

public class MissingOptionValueException extends ArgumentException {

    public MissingOptionValueException(String type, String option) {
        super("Type '" + type + "' is missing value for option '" + option + "'");
    }

}
