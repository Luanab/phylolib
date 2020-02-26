package file.matrix;

import data.matrix.Matrix;
import file.IReader;
import file.IWriter;

import java.util.HashMap;

public interface IMatrixFormatter extends IReader<Matrix>, IWriter<Matrix> {

	HashMap<String, IMatrixFormatter> FORMATTERS = new HashMap<>() {{
		put("csv", new CSV());
	}};

}
