package exception;

public class RepeatedOptionException extends ArgumentException {

	public RepeatedOptionException(String option) {
		super("Option '" + option + "' can only be defined once for every command call");
	}

}
