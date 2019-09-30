package exception;

public class NumberOfArgumentsException extends ParameterException {

	public NumberOfArgumentsException(String name, int number) {
		super("Parameter " + name + " receives at least " + number + " parameters...");
	}

}
