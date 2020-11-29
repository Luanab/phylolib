package data.matrix;

import cli.Format;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Responsible for parsing and formatting {@link Matrix distance matrices} from and to Strings.
 */
public abstract class SymmetryProcessor extends MatrixProcessor {

	/**
	 * Checks the symmetry of this distance matrix processor.
	 *
	 * @return a flag indicating whether the distance matrix is symmetric or asymmetric
	 */
	protected abstract boolean symmetric();

	@Override
	public final Matrix parse(Stream<String> data) {
		Iterator<String> iterator = data.iterator();
		String start;
		if (!iterator.hasNext() || !Format.NATURAL.matches(start = iterator.next()))
			return null;
		int size = Integer.parseInt(start);
		String[] ids = new String[size];
		Double[][] matrix = new Double[size][];
		int i = 0;
		while (iterator.hasNext()) {
			String[] next = iterator.next().split("\\t");
			String[] distances = Arrays.copyOfRange(next, 1, next.length);
			if (i >= size || distances.length != (symmetric() ? i : size) || !Arrays.stream(distances).allMatch(Format.DISTANCE::matches))
				return null;
			ids[i] = next[0];
			matrix[i++] = Arrays.stream(distances).map(Double::parseDouble).toArray(Double[]::new);
		}
		return i == size && size > 1 ? new Matrix(symmetric(), ids, matrix) : null;
	}

	@Override
	public final String parse(Matrix matrix) {
		StringBuilder data = new StringBuilder();
		int size = matrix.size();
		data.append(size);
		for (int i = 0; i < size; i++) {
			data.append('\n').append(matrix.ids()[i]);
			for (int j = 0; j < (symmetric() ? i : size); j++)
				data.append('\t').append(matrix.distance(i, j));
		}
		return data.toString();
	}

}
