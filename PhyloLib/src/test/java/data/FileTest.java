package data;

import data.dataset.IDatasetProcessor;
import data.matrix.IMatrixProcessor;
import data.tree.ITreeProcessor;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileTest {

	@Test
	public void get_MissingFile_Empty() {
		assertTrue(File.get("", IDatasetProcessor.PROCESSORS).isEmpty());
	}

	@Test
	public void get_MissingLocation_Empty() {
		assertTrue(File.get("mlst", ITreeProcessor.PROCESSORS).isEmpty());
	}

	@Test
	public void get_EmptyLocation_Empty() {
		assertTrue(File.get("fasta:", IDatasetProcessor.PROCESSORS).isEmpty());
	}

	@Test
	public void get_BlankLocation_Empty() {
		assertTrue(File.get("csv: ", IMatrixProcessor.PROCESSORS).isEmpty());
	}

	@Test
	public void get_InvalidFormat_Empty() {
		assertTrue(File.get("csc:matrix.csv", IMatrixProcessor.PROCESSORS).isEmpty());
	}

	@Test
	public void get_InvalidPath_Empty() {
		assertTrue(File.get("newick:C:/xpto?", ITreeProcessor.PROCESSORS).isEmpty());
	}

	@Test
	public void get_ValidFile_Success() {
		String arg = "nexus:output.txt";

		Optional<File<ITreeProcessor>> file = File.get(arg, ITreeProcessor.PROCESSORS);

		assertTrue(file.isPresent());
		assertEquals(file.get().getProcessor(), ITreeProcessor.PROCESSORS.get("nexus"));
		assertEquals(file.get().getPath().toString(), "output.txt");
	}

}
