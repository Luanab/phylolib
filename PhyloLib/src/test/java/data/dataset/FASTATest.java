package data.dataset;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class FASTATest {

	@Test
	public void parse_Empty_Empty() {
		assertEquals(new FASTA().parse(Stream.empty()).size(), 0);
	}

	@Test
	public void parse_Blank_Empty() {
		assertEquals(new FASTA().parse(Stream.of(" ")).size(), 0);
	}

	@Test
	public void parse_NoId_Ignore() {
		Stream<String> data = Stream.of("TACTGATC", "TACTGATC", ">2 profile", "TATTGGTC", ">3", "ATCTAGTC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.profile(0).length(), 8);
		assertEquals(dataset.profile(1).length(), 8);
	}

	@Test
	public void parse_NoSequence_Ignore() {
		Stream<String> data = Stream.of(">1", "TACTGATC", ">2 profile", ">3", "ATCTAGTC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.profile(0).length(), 8);
		assertEquals(dataset.profile(1).length(), 8);
	}

	@Test
	public void parse_NoLoci_Ignore() {
		Stream<String> data = Stream.of(">1", "", ">2 profile", "TACTGATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 8);
	}

	@Test
	public void parse_TooFewLoci_Ignore() {
		Stream<String> data = Stream.of(">1", "A", ">2 profile", "TACTGATC", "ACTGGATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 16);
	}

	@Test
	public void parse_LessLoci_Ignore() {
		Stream<String> data = Stream.of(">1", "ACTGG-TC", "ACTGGATC", ">2 profile", "TACTGATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 16);
		assertNull(dataset.profile(0).locus(5));
	}

	@Test
	public void parse_MoreLoci_Ignore() {
		Stream<String> data = Stream.of(">1", "ACTGG TC", ">2 profile", "TACTGATC", "ACTGGATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 8);
		assertNull(dataset.profile(0).locus(5));
	}

	@Test
	public void parse_InvalidLocus_Ignore() {
		Stream<String> data = Stream.of(">1", "ACTGG-TC", ">2 profile", "TACTGATC", "ACTGXATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 8);
		assertNull(dataset.profile(0).locus(5));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of(">1", "ACTGG TC", "ACTGCATC", ">2 profile", "TACTGATC", " CTGAATC");

		Dataset dataset = new FASTA().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.profile(0).length(), 16);
		assertNull(dataset.profile(0).locus(5));
		assertEquals(dataset.profile(1).length(), 16);
		assertNull(dataset.profile(1).locus(8));
	}

}
