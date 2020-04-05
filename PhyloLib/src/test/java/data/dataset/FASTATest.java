package data.dataset;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class FASTATest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new FASTA().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new FASTA().parse(Stream.of(" ")));
	}

	@Test
	public void parse_NoSequence_Ignore() {
		Stream<String> data = Stream.of(">1", "TACTGATC", ">2 profile", ">3", "ATCTAGTC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 8);
		assertEquals(dataset.get(1).getId(), "3");
		assertEquals(dataset.get(1).length(), 8);
	}

	@Test
	public void parse_SequenceWithNoLength_Ignore() {
		Stream<String> data = Stream.of(">1", "", ">2 profile", "TACTGATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "2 profile");
		assertEquals(dataset.get(0).length(), 8);
	}

	@Test
	public void parse_SequenceWithDifferentLength_Ignore() {
		Stream<String> data = Stream.of(">1", "ACTGG TC", "ACTGGATC", ">2 profile", "TACTGATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 16);
		assertNull(dataset.get(0).getLocus(5));
	}

	@Test
	public void parse_SequenceWithInvalidLocus_Ignore() {
		Stream<String> data = Stream.of(">1", "ACTGG TC", ">2 profile", "TACTGATC", "ACTGXATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 8);
		assertNull(dataset.get(0).getLocus(5));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of(">1", "ACTGG TC", "ACTGCATC", ">2 profile", "TACTGATC", " CTGAATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.get(0).getId(), "1");
		assertEquals(dataset.get(0).length(), 16);
		assertNull(dataset.get(0).getLocus(5));
		assertEquals(dataset.get(1).getId(), "2 profile");
		assertEquals(dataset.get(1).length(), 16);
		assertNull(dataset.get(1).getLocus(8));
	}

}
