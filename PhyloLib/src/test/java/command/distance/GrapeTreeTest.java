package command.distance;

import data.dataset.Profile;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GrapeTreeTest {

	@Test
	public void distance_Equal_Zero() {
		assertEquals(new GrapeTree().distance(new Profile("000"), new Profile("000")), 0);
	}

	@Test
	public void distance_OneMissing_Zero() {
		assertEquals(new GrapeTree().distance(new Profile(" 00000"), new Profile(" 00000")), 0);
	}

	@Test
	public void distance_OneMissingInFirst_OneSixth() {
		assertEquals(new GrapeTree().distance(new Profile("00 000"), new Profile("000000")), 1.0 / 6.0);
	}

	@Test
	public void distance_OneMissingInSecond_Zero() {
		assertEquals(new GrapeTree().distance(new Profile("010000"), new Profile("0 0000")), 0);
	}

	@Test
	public void distance_DifferentMissingInBoth_OneFifth() {
		assertEquals(new GrapeTree().distance(new Profile(" 00000"), new Profile("0 0000")), 1.0 / 5.0);
	}

	@Test
	public void distance_TwoDifferent_TwoFourths() {
		assertEquals(new GrapeTree().distance(new Profile("0010"), new Profile("0100")), 2.0 / 4.0);
	}

	@Test
	public void distance_OneMissingAndOneDifferent_OneFourth() {
		assertEquals(new GrapeTree().distance(new Profile("010 1"), new Profile("011 1")), 1.0 / 4.0);
	}

	@Test
	public void distance_AllDifferent_One() {
		assertEquals(new GrapeTree().distance(new Profile("10010"), new Profile("01101")), 1);
	}

}
