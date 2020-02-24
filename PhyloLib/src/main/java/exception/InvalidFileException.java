package exception;

public class InvalidFileException extends ArgumentException {

	public InvalidFileException(String file) {
		super("File '" + file + "' is invalid");
	}

}
