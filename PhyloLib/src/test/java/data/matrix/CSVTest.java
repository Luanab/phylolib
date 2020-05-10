package data.matrix;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class CSVTest {

	@DataProvider
	public Object[][] invalid() {
		return new Object[][] {
				{ Stream.empty() },
				{ Stream.of(" ") },
				{ Stream.of("0.0") },
				{ Stream.of("0.0", "1.0") }
		};
	}

	@Test(dataProvider = "invalid")
	public void parse_Invalid_Null(Stream<String> rows) {
		assertNull(new CSV().parse(rows));
	}

	@DataProvider
	public Object[][] valid() {
		return new Object[][] {
				{ "0\t4.5", "4.5\tb", "4.5\t0" },
				{ "0\t4.5", "4.5\t0", "7\t0" },
				{ "0\t4.5\t3", "4.5\t0\t0" },
				{ "0\t4.5\t", "4.5\t0" },
		};
	}

	@Test(dataProvider = "valid")
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
		assertEquals(new CSV().format(new Matrix(false, new String[0], (i, j) -> 0)), "");
	}

	@Test
	public void format_Valid_Success() {
		Matrix matrix = new Matrix(false, new String[2], (i, j) -> 50.36);

		String data = new CSV().format(matrix);

		assertEquals(data, "0.0\t50.36\n50.36\t0.0");
	}

}
