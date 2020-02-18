package data.matrix;

import data.IFormatter;
import data.IReader;
import data.IWriter;
import exception.InvalidFileFormatException;

import java.util.HashMap;

public interface IMatrixFormatter extends IReader<Matrix>, IWriter<Matrix> {

	static IMatrixFormatter get(String format) throws InvalidFileFormatException {
		return IFormatter.get(format, new HashMap<>() {{
			put("csv", CSVFormatter::new);
		}});
	}

}
