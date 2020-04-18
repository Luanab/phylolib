package command.correction;

import data.matrix.Matrix;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CorrectionTest {

	@Test
	public void process_Empty_Empty() {
		assertEquals(new JukesCantor().process(new Matrix(new String[0], (i, j) -> 0)).size(), 0);
	}

	@Test
	public void process_Valid_Full() {
		Double[][] distances = new Double[2][2];
		distances[0][0] = 0.0;
		distances[0][1] = 0.64875;
		distances[1][0] = 0.64875;
		distances[1][1] = 0.0;
		Matrix matrix = new Matrix(new String[2], (i, j) -> distances[i][j]);

		Matrix corrected = new JukesCantor().process(matrix);

		assertEquals(corrected.size(), 2);
		assertEquals(corrected.get(0, 0), 0);
		assertEquals(corrected.get(0, 1), -0.75 * Math.log(0.135));
		assertEquals(corrected.get(1, 0), -0.75 * Math.log(0.135));
		assertEquals(corrected.get(1, 1), 0);
	}

}
