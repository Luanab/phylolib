package pt.ist.phylolib.command.distance;

import pt.ist.phylolib.data.dataset.Profile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GrapeTreeTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ "000", "000", 0 },
				{ " 00000", " 00000", 0 },
				{ "00 000", "000000", 1.0 / 6.0 },
				{ "010000", "0 0000", 0 },
				{ " 00000", "0 0000", 1.0 / 5.0 },
				{ "0010", "0100", 2.0 / 4.0 },
				{ "010 1", "011 1", 1.0 / 4.0 },
				{ "10010", "01101", 1 }
		};
	}

	@Test(dataProvider = "data")
	public void distance_Valid_Success(String i, String j, double result) {
		assertEquals(new GrapeTree().distance(new Profile(null, i), new Profile(null, j)), result);
	}

}
