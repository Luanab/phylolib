package data;

import data.tree.Nexus;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileTest {

	@Test
	public void get_MissingFile_Empty() {
		assertTrue(File.get("", Processor.DATASET).isEmpty());
	}

	@Test
	public void get_MissingLocation_Empty() {
		assertTrue(File.get("mlst", Processor.TREE).isEmpty());
	}

	@Test
	public void get_EmptyLocation_Empty() {
		assertTrue(File.get("fasta:", Processor.DATASET).isEmpty());
	}

	@Test
	public void get_BlankLocation_Empty() {
		assertTrue(File.get("csv: ", Processor.MATRIX).isEmpty());
	}

	@Test
	public void get_InvalidFormat_Empty() {
		assertTrue(File.get("csc:matrix.csv", Processor.MATRIX).isEmpty());
	}

	@Test
	public void get_InvalidPath_Empty() {
		assertTrue(File.get("newick:C:/xpto?", Processor.TREE).isEmpty());
	}

	@Test
	public void get_ValidFile_Success() {
		String arg = "nexus:output.txt";

		Optional<File> file = File.get(arg, Processor.TREE);

		assertTrue(file.isPresent());
		assertTrue(file.get().getProcessor() instanceof Nexus);
		assertEquals(file.get().getPath().toString(), "output.txt");
	}

}
