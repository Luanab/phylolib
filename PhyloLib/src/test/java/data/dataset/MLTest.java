package data.dataset;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MLTest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new ML().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new ML().parse(Stream.of(" ")));
	}

	@Test
	public void parse_NoSequence_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2");
		assertEquals(dataset.get(0).length(), 4);
	}

	@Test
	public void parse_SequenceWithNoLength_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2");
		assertEquals(dataset.get(0).length(), 4);
	}

	@Test
	public void parse_SequenceWithDifferentLength_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t1\t \t1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 3);
		assertNull(dataset.get(0).getLocus(1));
	}

	@Test
	public void parse_SequenceWithInvalidLocus_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t \t1\tx\t1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2");
		assertEquals(dataset.get(0).length(), 4);
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t \t1\t1\t1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 4);
		assertNull(dataset.get(0).getLocus(0));
		assertEquals(dataset.get(1).getId(), "2");
		assertEquals(dataset.get(1).length(), 4);
	}

}
