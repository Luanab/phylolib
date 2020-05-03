package data.matrix;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CSVTest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new CSV().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new CSV().parse(Stream.of(" ")));
	}

	@DataProvider
	public Object[][] rows() {
		return new Object[][] {
				{ "0\t4.5", "4.5\tb", "4.5\t0" },
				{ "0\t4.5", "4.5\t0", "7\t0" },
				{ "0\t4.5\t3", "4.5\t0\t0" },
				{ "0\t4.5\t", "4.5\t0" },
		};
	}

	@Test(dataProvider = "rows")
	public void parse_Valid_Success(String... rows) {
		Stream<String> data = Stream.of(rows);

		Matrix matrix = new CSV().parse(data);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.distance(0, 0), 0);
		assertEquals(matrix.distance(0, 1), 4.5);
		assertEquals(matrix.distance(1, 0), 4.5);
		assertEquals(matrix.distance(1, 1), 0);
	}

	@Test
	public void format_Empty_Empty() {
		assertEquals(new CSV().format(new Matrix(new String[0], (i, j) -> 0)), "");
	}

	@Test
	public void format_Valid_Success() {
		Double[][] distances = new Double[2][2];
		distances[0][0] = 0.0;
		distances[0][1] = 50.36;
		distances[1][0] = 50.36;
		distances[1][1] = 0.0;
		Matrix matrix = new Matrix(new String[2], (i, j) -> distances[i][j]);

		String data = new CSV().format(matrix);

		assertEquals(data, "0.0\t50.36\n50.36\t0.0");
	}

}
