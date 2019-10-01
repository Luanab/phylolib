package flow.read;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class FileReader extends Reader {

	private final String store;

	public FileReader(List<String> values) throws NumberOfArgumentsException {
		super(values, 1);
		this.store = values.get(1);
	}

	@Override
	public String read() {
		return null;
	}

}
