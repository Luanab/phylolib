package exception;

public class RepeatedParameterException extends ParameterException {

    public RepeatedParameterException(String name) {
        super("Parameter " + name + " can only be defined once...");
    }

}
