package exception;

public class RepeatedOptionException extends ArgumentException {

	public RepeatedOptionException(String option) {
		super("Option '" + option + "' is repeated in same command call");
	}

}
