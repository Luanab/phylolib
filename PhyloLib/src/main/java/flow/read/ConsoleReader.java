package flow.read;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class ConsoleReader extends Reader {

	public ConsoleReader(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public String read() {
		return null;
	}

}
