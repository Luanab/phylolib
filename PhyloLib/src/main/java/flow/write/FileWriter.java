package flow.write;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class FileWriter extends Writer {

	private final String store;

	public FileWriter(List<String> values) throws NumberOfArgumentsException {
		super(values, 1);
		this.store = values.get(0);
	}

	@Override
	public void write(String data) {

	}

}
