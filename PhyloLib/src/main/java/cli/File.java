package cli;

import exception.InvalidFileException;

public final class File {

	private final String format;
	private final String location;

	public File(String file) throws InvalidFileException {
		String[] values = file.split(":", 2);
		if (values.length == 1 || values[1].isBlank())
			throw new InvalidFileException(file);
		this.format = values[0];
		this.location = values[1];
	}

	public String getFormat() {
		return format;
	}

	public String getLocation() {
		return location;
	}

}
