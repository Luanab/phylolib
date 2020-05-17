package cli;

public enum Format {

	FILE("^\\w+:.+$"),
	NATURAL("^\\d+$"),
	DISTANCE("^(\\d*(\\.\\d+(E-\\d+)?)?)$");

	private final String regex;

	Format(String regex) {
		this.regex = regex;
	}

	public boolean matches(String string) {
		return string.matches(regex);
	}

}
