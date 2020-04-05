package data.matrix;

import data.IReader;
import data.IWriter;

import java.util.HashMap;

public interface IMatrixProcessor extends IReader<Matrix>, IWriter<Matrix> {

	String INVALID_ROW = "Ignoring invalid row %d";
	String INVALID_COLUMN = "Ignoring invalid column %d";

	HashMap<String, IMatrixProcessor> PROCESSORS = new HashMap<>() {{
		put("csv", new CSV());
	}};

}
