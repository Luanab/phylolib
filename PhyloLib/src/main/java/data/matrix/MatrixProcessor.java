package data.matrix;

import data.IReader;
import data.IWriter;

public abstract class MatrixProcessor implements IReader<Matrix>, IWriter<Matrix> {

	protected static final String INVALID_ROW = "Ignored invalid row %d";
	protected static final String INVALID_COLUMN = "Ignored invalid column %d";

}
