package cli;

public final class Parameters {

	private final String type;
	private final Options options;

	public Parameters(String type, Options options) {
		this.type = type;
		this.options = options;
	}

	public String getType() {
		return type;
	}

	public Options getOptions() {
		return options;
	}

}
