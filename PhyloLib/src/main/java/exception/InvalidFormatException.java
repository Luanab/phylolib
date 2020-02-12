package exception;

public abstract class InvalidFormatException extends ArgumentException {

    public InvalidFormatException(String name, String value) {
        super("Invalid " + name + " format '" + value + "'");
    }

}
