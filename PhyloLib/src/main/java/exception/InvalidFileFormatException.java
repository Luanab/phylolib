package exception;

public class InvalidFileFormatException extends InvalidFormatException {

	public InvalidFileFormatException(String value) {
		super("file", value);
	}

}
