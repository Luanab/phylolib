package data.matrix;

import java.util.Arrays;
import java.util.stream.Stream;

public final class CSV implements IMatrixFormatter {

	@Override
	public Matrix parse(Stream<String> data) {
		Double[][] matrix = data
				.map(line -> Arrays.stream(line.split("\\t"))
						.map(Double::parseDouble)
						.toArray(Double[]::new))
				.toArray(Double[][]::new);
		return new Matrix(matrix.length, (i, j) -> matrix[i][j]);
	}

	@Override
	public String format(Matrix matrix) {
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < matrix.size(); i++) {
			for (int j = 0; j < matrix.size(); j++) {
				data.append(matrix.get(i, j));
				data.append('\t');
			}
			data.replace(data.length() - 1, data.length(), "\n");
		}
		data.deleteCharAt(data.length() - 1);
		return data.toString();
	}

}
