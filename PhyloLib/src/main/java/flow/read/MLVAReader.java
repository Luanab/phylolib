package flow.read;

import data.DataSet;

import java.util.stream.Stream;

public final class MLVAReader extends Reader {

	MLVAReader(String from) {
		super(from);
	}

	@Override
	protected DataSet parse(Stream<String> data) {
		return null;
	}

}
