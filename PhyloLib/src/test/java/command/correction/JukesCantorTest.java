package command.correction;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class JukesCantorTest {

	@Test
	public void correct_OffRange_NaN() {
		assertTrue(Double.isNaN(new JukesCantor().correct(0.76)));
	}

	@Test
	public void correct_Max_Infinity() {
		assertTrue(Double.isInfinite(new JukesCantor().correct(0.75)));
	}

	@Test
	public void correct_Zero_Zero() {
		assertEquals(new JukesCantor().correct(0), 0);
	}

	@Test
	public void correct_OnRange_Value() {
		assertEquals(new JukesCantor().correct(0.64875), -0.75 * Math.log(0.135));
	}

}
