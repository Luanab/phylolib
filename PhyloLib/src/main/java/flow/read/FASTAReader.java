package flow.read;

import data.DataSet;

import java.util.stream.Stream;

public final class FASTAReader extends Reader {

	FASTAReader(String from) {
		super(from);
	}

	@Override
	protected DataSet parse(Stream<String> data) {
		return null;
	}

}
