package flow.read;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class LinkReader extends Reader {

	private final String link;

	public LinkReader(List<String> values) throws NumberOfArgumentsException {
		super(values, 1);
		this.link = values.get(1);
	}

	@Override
	public String read() {
		return null;
	}

}
