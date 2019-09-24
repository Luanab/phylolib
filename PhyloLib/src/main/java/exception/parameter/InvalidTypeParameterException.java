package exception.parameter;

public class InvalidTypeParameterException extends ParameterException {

	public InvalidTypeParameterException(String name) {
		super("Invalid " + name + " parameter type...");
	}

}
