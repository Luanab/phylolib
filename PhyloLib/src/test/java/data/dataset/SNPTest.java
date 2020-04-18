package data.dataset;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class SNPTest {

	@Test
	public void parse_Empty_Empty() {
		assertEquals(new SNP().parse(Stream.empty()).size(), 0);
	}

	@Test
	public void parse_Blank_Empty() {
		assertEquals(new SNP().parse(Stream.of(" ")).size(), 0);
	}

	@Test
	public void parse_NoSequence_Ignore() {
		Stream<String> data = Stream.of("", "2\t100-1");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 5);
		assertNull(dataset.profile(0).locus(3));
	}

	@Test
	public void parse_NoLoci_Ignore() {
		Stream<String> data = Stream.of("1\t", "2\t100010");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 6);
	}

	@Test
	public void parse_TooFewLoci_Ignore() {
		Stream<String> data = Stream.of("1\t1", "2\t10001");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 5);
	}

	@Test
	public void parse_LessLoci_Ignore() {
		Stream<String> data = Stream.of("1\t1101 1", "2\t10001");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 6);
		assertNull(dataset.profile(0).locus(4));
	}

	@Test
	public void parse_MoreLoci_Ignore() {
		Stream<String> data = Stream.of("1\t1101", "2\t10001");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 4);
	}

	@Test
	public void parse_InvalidLocus_Ignore() {
		Stream<String> data = Stream.of("1\t11x1 1", "2\t1-001");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 1);
		assertEquals(dataset.profile(0).length(), 5);
		assertNull(dataset.profile(0).locus(1));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("1\t1101 1", "2\t100010");

		Dataset dataset = new SNP().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.profile(0).length(), 6);
		assertNull(dataset.profile(0).locus(4));
		assertEquals(dataset.profile(1).length(), 6);
		assertEquals(dataset.profile(1).locus(4), Integer.valueOf('1'));
	}

}
