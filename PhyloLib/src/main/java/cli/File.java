package cli;

public final class File {

	private final String format;
	private final String location;

	public File(String file) {
		String[] values = file.split(":", 2);
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
