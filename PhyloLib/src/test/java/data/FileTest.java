package data;

import data.tree.Nexus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FileTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ "", Processor.DATASET },
				{ "mlst", Processor.TREE },
				{ "fasta:", Processor.DATASET },
				{ "csv: ", Processor.MATRIX },
				{ "csc:matrix.csv", Processor.MATRIX },
				{ "newick:C:/xpto?", Processor.TREE }
		};
	}

	@Test(dataProvider = "data")
	public void get_Invalid_Empty(String file, Processor processor) {
		assertTrue(File.get(file, processor).isEmpty());
	}

	@Test
	public void get_Valid_Success() {
		String arg = "nexus:output.txt";

		Optional<File> file = File.get(arg, Processor.TREE);

		assertTrue(file.isPresent());
		assertTrue(file.get().getProcessor() instanceof Nexus);
		assertEquals(file.get().getPath().toString(), "output.txt");
	}

}
