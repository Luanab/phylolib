package exception;

public class MandatoryParameterException extends ParameterException {

    public MandatoryParameterException(String name) {
        super("Parameter " + name + " is mandatory...");
    }

}
