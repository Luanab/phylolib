package flow.write;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class ConsoleWriter extends Writer {

	public ConsoleWriter(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public void write(String data) {

	}

}
