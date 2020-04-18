package command.distance;

import data.dataset.Profile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class KimuraTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ "ACTG", "ACTG", 0 },
				{ "A TG", "A TG", 0 },
				{ "A TG", "ACTG", 0 },
				{ "ACTG", "A TG", 0 },
				{ "ACTG", "GCTG", -Math.log(0.5) / 2.0 },
				{ "ACTG", "ACGG", -Math.log(0.75 * Math.sqrt(0.5)) / 2.0 },
				{ "ACTG", "GCGG", -Math.log(0.25 * Math.sqrt(0.5)) / 2.0 }
		};
	}

	@Test(dataProvider = "data")
	public void distance_Valid_Success(String i, String j, double result) {
		assertEquals(new Kimura().distance(new Profile(null, i), new Profile(null, j)), result);
	}

}
