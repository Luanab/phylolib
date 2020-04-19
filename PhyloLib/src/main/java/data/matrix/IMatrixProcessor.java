package data.matrix;

import data.IReader;
import data.IWriter;

public interface IMatrixProcessor extends IReader<Matrix>, IWriter<Matrix> {

	String INVALID_ROW = "Ignored invalid row %d";
	String INVALID_COLUMN = "Ignored invalid column %d";

}
