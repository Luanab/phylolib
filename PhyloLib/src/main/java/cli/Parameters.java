package cli;

import java.lang.reflect.Constructor;

public final class Parameters {

	private final Constructor<?> type;
	private final Options options;

	public Parameters(Constructor<?> type, Options options) {
		this.type = type;
		this.options = options;
	}

	public Constructor<?> type() {
		return type;
	}

	public Options options() {
		return options;
	}

}
