package exception;

public class NumberOfArgumentsException extends ParameterException {

	public NumberOfArgumentsException(String name, int number) {
		super("Number of arguments for parameter " + name + " should be " + number + "...");
	}

}
