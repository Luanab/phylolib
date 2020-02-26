package cli;

public enum Format {

	FILE("^\\w+:.+$"), NATURAL("^\\d+$");

	public final String regex;

	Format(String regex) {
		this.regex = regex;
	}

}
