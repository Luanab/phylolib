package flow.read;

import data.DataSet;

import java.util.stream.Stream;

public final class MLSTReader extends Reader {

	MLSTReader(String from) {
		super(from);
	}

	@Override
	protected DataSet parse(Stream<String> data) {
		return null;
	}

}
