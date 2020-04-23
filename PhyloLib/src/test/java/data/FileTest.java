package data;

import cli.Processor;
import data.tree.Nexus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

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
		assertNull(File.get(file, processor));
	}

	@Test
	public void get_Valid_Success() {
		String arg = "nexus:output.txt";

		File file = File.get(arg, Processor.TREE);

		assertNotNull(file);
		assertTrue(file.processor() instanceof Nexus);
		assertEquals(file.path().toString(), "output.txt");
	}

}
