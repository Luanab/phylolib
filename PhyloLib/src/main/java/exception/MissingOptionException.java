package exception;

public class MissingOptionException extends ArgumentException {

    public MissingOptionException(String type, String option) {
        super("Type '" + type + "' is missing option '" + option + "'");
    }

}
