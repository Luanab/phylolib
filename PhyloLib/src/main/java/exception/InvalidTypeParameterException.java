package exception;

public class InvalidTypeParameterException extends ParameterException {

    public InvalidTypeParameterException(String name, String value) {
        super("Invalid value " + value + " for parameter " + name + "...");
    }

}
