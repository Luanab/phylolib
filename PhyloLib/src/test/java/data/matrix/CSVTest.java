package data.matrix;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class CSVTest {

	@Test
	public void parse_Empty_Empty() {
		assertEquals(new CSV().parse(Stream.empty()).size(), 0);
	}

	@Test
	public void parse_Blank_Empty() {
		assertEquals(new CSV().parse(Stream.of(" ")).size(), 0);
	}

	@Test
	public void parse_InvalidDistance_Ignore() {
		Stream<String> data = Stream.of("0\t2", "2\tb", "2\t0");

		Matrix matrix = new CSV().parse(data);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.get(0, 0), 0);
		assertEquals(matrix.get(0, 1), 2);
		assertEquals(matrix.get(1, 0), 2);
		assertEquals(matrix.get(1, 1), 0);
	}

	@Test
	public void parse_TooManyRows_Ignore() {
		Stream<String> data = Stream.of("0\t4.5", "4.5\t0", "7\t0");

		Matrix matrix = new CSV().parse(data);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.get(0, 0), 0);
		assertEquals(matrix.get(0, 1), 4.5);
		assertEquals(matrix.get(1, 0), 4.5);
		assertEquals(matrix.get(1, 1), 0);
	}

	@Test
	public void parse_TooManyColumns_Ignore() {
		Stream<String> data = Stream.of("0\t4.5\t3", "4.5\t0\t0");

		Matrix matrix = new CSV().parse(data);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.get(0, 0), 0);
		assertEquals(matrix.get(0, 1), 4.5);
		assertEquals(matrix.get(1, 0), 4.5);
		assertEquals(matrix.get(1, 1), 0);
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("0\t3.14\t", "15\t0");

		Matrix matrix = new CSV().parse(data);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.get(0, 0), 0);
		assertEquals(matrix.get(0, 1), 3.14);
		assertEquals(matrix.get(1, 0), 15);
		assertEquals(matrix.get(1, 1), 0);
	}

	@Test
	public void format_Empty_Empty() {
		assertEquals(new CSV().format(new Matrix(0, null)), "");
	}

	@Test
	public void format_Valid_Success() {
		Double[][] distances = new Double[2][2];
		distances[0][0] = 0.0;
		distances[0][1] = 50.36;
		distances[1][0] = 50.36;
		distances[1][1] = 0.0;
		Matrix matrix = new Matrix(2, (i, j) -> distances[i][j]);

		String data = new CSV().format(matrix);

		assertEquals(data, "0.0\t50.36\n50.36\t0.0");
	}

}
