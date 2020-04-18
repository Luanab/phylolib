package data.matrix;

import data.IReader;
import data.IWriter;

public interface IMatrixProcessor extends IReader<Matrix>, IWriter<Matrix> {

	String IGNORING_ROW = "Ignoring invalid row %d";
	String IGNORING_COLUMN = "Ignoring invalid column %d";

}
