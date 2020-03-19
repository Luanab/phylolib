package data;

import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.tree.ITreeFormatter;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileTest {

	@Test
	public void get_MissingFile_Empty() {
		assertTrue(File.get("", IDatasetFormatter.FORMATTERS).isEmpty());
	}

	@Test
	public void get_MissingLocation_Empty() {
		assertTrue(File.get("mlst", ITreeFormatter.FORMATTERS).isEmpty());
	}

	@Test
	public void get_EmptyLocation_Empty() {
		assertTrue(File.get("fasta:", IDatasetFormatter.FORMATTERS).isEmpty());
	}

	@Test
	public void get_BlankLocation_Empty() {
		assertTrue(File.get("csv: ", IMatrixFormatter.FORMATTERS).isEmpty());
	}

	@Test
	public void get_InvalidFormat_Empty() {
		assertTrue(File.get("csc:matrix.csv", IMatrixFormatter.FORMATTERS).isEmpty());
	}

	@Test
	public void get_InvalidPath_Empty() {
		assertTrue(File.get("newick:C:/xpto?", ITreeFormatter.FORMATTERS).isEmpty());
	}

	@Test
	public void get_ValidFile_Success() {
		String arg = "nexus:output.txt";

		Optional<File<ITreeFormatter>> file = File.get(arg, ITreeFormatter.FORMATTERS);

		assertTrue(file.isPresent());
		assertEquals(file.get().getFormatter(), ITreeFormatter.FORMATTERS.get("nexus"));
		assertEquals(file.get().getPath().toString(), "output.txt");
	}

}
