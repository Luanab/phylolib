package exception;

public class RepeatedCommandException extends ArgumentException {

    public RepeatedCommandException(String command) {
        super("Command '" + command + "' can only be called once");
    }

}
