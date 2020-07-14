package data;

import cli.Data;
import data.tree.Nexus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ "", Data.DATASET },
				{ "mlst", Data.TREE },
				{ "fasta:", Data.DATASET },
				{ "csv: ", Data.MATRIX },
				{ "csc:matrix.csv", Data.MATRIX },
				{ "newick:C:/xpto?", Data.TREE }
		};
	}

	@Test(dataProvider = "data")
	public void get_Invalid_Empty(String file, Data data) {
		assertNull(File.get(file, data));
	}

	@Test
	public void get_Valid_Success() {
		String arg = "nexus:output.txt";

		File file = File.get(arg, Data.TREE);

		assertNotNull(file);
		assertTrue(file.processor() instanceof Nexus);
		assertEquals(file.path().toString(), "output.txt");
	}

}
