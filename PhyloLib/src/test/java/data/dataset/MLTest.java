package data.dataset;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MLTest {

	@Test
	public void parse_Empty_Empty() {
		assertEquals(new ML().parse(Stream.empty()).size(), 0);
	}

	@Test
	public void parse_Blank_Empty() {
		assertEquals(new ML().parse(Stream.of(" ")).size(), 0);
	}

	@Test
	public void parse_NoSequence_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 4);
	}

	@Test
	public void parse_NoLoci_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 4);
	}

	@Test
	public void parse_TooFewLoci_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 4);
	}

	@Test
	public void parse_LessLoci_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t1\t-\t1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 3);
		assertNull(dataset.profile(0).locus(1));
	}

	@Test
	public void parse_MoreLoci_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t1\t\t1", "2\t1\t2");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 3);
		assertNull(dataset.profile(0).locus(1));
	}

	@Test
	public void parse_InvalidLocus_Ignore() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t \t1\tx\t1", "2\t1\t2\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 3);
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("ST\taroe\taroi\tarou", "1\t \t1\t1\t1", "2\t1\t2\t1\t1");

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.profile(0).length(), 4);
		assertNull(dataset.profile(0).locus(0));
		assertEquals(dataset.profile(1).length(), 4);
		assertEquals(dataset.profile(1).locus(1), Integer.valueOf(2));
	}

}
