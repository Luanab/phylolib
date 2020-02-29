package data;

import data.dataset.IDatasetFormatter;
import data.matrix.IMatrixFormatter;
import data.tree.ITreeFormatter;
import exception.InvalidFileException;
import exception.InvalidFormatException;
import org.testng.annotations.Test;

import java.nio.file.InvalidPathException;

import static org.testng.Assert.assertEquals;

public class FileTest {

	@Test(expectedExceptions = InvalidFileException.class)
	public void constructor_MissingLocation_ExceptionThrown() throws InvalidFileException, InvalidFormatException {
		new File<>("mlst", IDatasetFormatter.FORMATTERS);
	}

	@Test(expectedExceptions = InvalidFileException.class)
	public void constructor_EmptyLocation_ExceptionThrown() throws InvalidFileException, InvalidFormatException {
		new File<>("fasta:", IDatasetFormatter.FORMATTERS);
	}

	@Test(expectedExceptions = InvalidFileException.class)
	public void constructor_BlankLocation_ExceptionThrown() throws InvalidFileException, InvalidFormatException {
		new File<>("csv: ", IMatrixFormatter.FORMATTERS);
	}

	@Test(expectedExceptions = InvalidFormatException.class)
	public void constructor_InvalidFormat_ExceptionThrown() throws InvalidFileException, InvalidFormatException {
		new File<>("csc:matrix.csv", IMatrixFormatter.FORMATTERS);
	}

	@Test(expectedExceptions = InvalidPathException.class)
	public void constructor_InvalidPath_ExceptionThrown() throws InvalidFileException, InvalidFormatException {
		new File<>("newick:C:/xpto?", ITreeFormatter.FORMATTERS);
	}

	@Test
	public void constructor_ValidFormat_Success() throws InvalidFileException, InvalidFormatException {
		String arg = "nexus:output.txt";

		File<ITreeFormatter> file = new File<>(arg, ITreeFormatter.FORMATTERS);

		assertEquals(file.getFormatter(), ITreeFormatter.FORMATTERS.get("nexus"));
		assertEquals(file.getPath().toString(), "output.txt");
	}

}
