package command.correction;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class JukesCantorTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ 0.76, Double.NaN },
				{ 0.75, Double.POSITIVE_INFINITY },
				{ 0, 0 },
				{ 0.64875, -0.75 * Math.log(0.135) }
		};
	}

	@Test(dataProvider = "data")
	public void correct_Valid_Success(double distance, double result) {
		assertEquals(new JukesCantor().correct(distance), result);
	}

}
