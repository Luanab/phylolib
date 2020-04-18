package command.distance;

import data.dataset.Profile;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HammingTest {

	@Test
	public void distance_Equal_Zero() {
		assertEquals(new Hamming().distance(new Profile("000"), new Profile("000")), 0);
	}

	@Test
	public void distance_OneMissing_One() {
		assertEquals(new Hamming().distance(new Profile("0 0000"), new Profile("0 0000")), 1);
	}

	@Test
	public void distance_OneMissingInFirst_One() {
		assertEquals(new Hamming().distance(new Profile("0 0000"), new Profile("000000")), 1);
	}

	@Test
	public void distance_OneMissingInSecond_One() {
		assertEquals(new Hamming().distance(new Profile("010000"), new Profile("0 0000")), 1);
	}

	@Test
	public void distance_TwoDifferent_Two() {
		assertEquals(new Hamming().distance(new Profile("00100"), new Profile("01000")), 2);
	}

	@Test
	public void distance_OneMissingAndOneDifferent_Two() {
		assertEquals(new Hamming().distance(new Profile("010 1"), new Profile("011 1")), 2);
	}

	@Test
	public void distance_AllDifferent_Length() {
		assertEquals(new Hamming().distance(new Profile("10010"), new Profile("01101")), 5);
	}

}
