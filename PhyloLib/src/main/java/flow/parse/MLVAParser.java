package flow.parse;

import data.DataSet;
import exception.NumberOfArgumentsException;

import java.util.List;

public final class MLVAParser extends Parser {

	public MLVAParser(List<String> values) throws NumberOfArgumentsException {
		super(values, 0);
	}

	@Override
	public DataSet parse(String data) {
		return null;
	}

}
