package command.correction;

import data.matrix.Matrix;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CorrectionTest {

	@Test
	public void process_Valid_Full() {
		Matrix matrix = new Matrix(true, new String[2], (i, j) -> 0.64875);

		Matrix corrected = new JukesCantor().process(matrix);

		assertEquals(corrected.size(), 2);
		assertEquals(corrected.distance(0, 0), 0);
		assertEquals(corrected.distance(0, 1), -0.75 * Math.log(0.135));
		assertEquals(corrected.distance(1, 0), -0.75 * Math.log(0.135));
		assertEquals(corrected.distance(1, 1), 0);
	}

}
