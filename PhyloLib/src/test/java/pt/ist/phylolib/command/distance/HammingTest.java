package pt.ist.phylolib.command.distance;

import pt.ist.phylolib.data.dataset.Profile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HammingTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ "000", "000", 0 },
				{ "0 0000", "0 0000", 1 },
				{ "0 0000", "000000", 1 },
				{ "010000", "0 0000", 1 },
				{ "00100", "01000", 2 },
				{ "010 1", "011 1", 2 },
				{ "10010", "01101", 5 }
		};
	}

	@Test(dataProvider = "data")
	public void distance_Valid_Success(String i, String j, double result) {
		assertEquals(new Hamming().distance(new Profile(null, i), new Profile(null, j)), result);
	}

}
