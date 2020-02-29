package data.matrix;

import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface IMatrixFormatter extends IReader<Matrix>, IWriter<Matrix> {

	HashMap<String, IMatrixFormatter> FORMATTERS = new HashMap<>() {{
		put("csv", new CSV());
	}};

}
