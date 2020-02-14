package exception;

public class MissingOptionValueException extends ArgumentException {

    public MissingOptionValueException(String option) {
        super("Option '" + option + "' is missing a value");
    }

}
