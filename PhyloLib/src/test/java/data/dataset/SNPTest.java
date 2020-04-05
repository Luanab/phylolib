package data.dataset;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class SNPTest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new SNP().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new SNP().parse(Stream.of(" ")));
	}

	@Test
	public void parse_NoSequence_Ignore() {
		Stream<String> data = Stream.of("", "2\t100 1");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2");
		assertEquals(dataset.get(0).length(), 5);
		assertNull(dataset.get(0).getLocus(3));
	}

	@Test
	public void parse_SequenceWithNoLength_Ignore() {
		Stream<String> data = Stream.of("1\t", "2\t100010");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2");
		assertEquals(dataset.get(0).length(), 6);
	}

	@Test
	public void parse_SequenceWithDifferentLength_Ignore() {
		Stream<String> data = Stream.of("1\t1101 1", "2\t10001");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 6);
		assertNull(dataset.get(0).getLocus(4));
	}

	@Test
	public void parse_SequenceWithInvalidLocus_Ignore() {
		Stream<String> data = Stream.of("1\t11x1 1", "2\t1 001");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2");
		assertEquals(dataset.get(0).length(), 5);
		assertNull(dataset.get(0).getLocus(1));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("1\t1101 1", "2\t100010");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 6);
		assertNull(dataset.get(0).getLocus(4));
		assertEquals(dataset.get(1).getId(), "2");
		assertEquals(dataset.get(1).length(), 6);
	}

}
