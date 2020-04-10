package command.distance;

import data.dataset.Profile;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class KimuraTest {

	@Test
	public void distance_Equal_Zero() {
		assertEquals(new Kimura().distance(new Profile("ACTG"), new Profile("ACTG")), 0);
	}

	@Test
	public void distance_OneMissing_Zero() {
		assertEquals(new Kimura().distance(new Profile("A TG"), new Profile("A TG")), 0);
	}

	@Test
	public void distance_OneMissingInFirst_Zero() {
		assertEquals(new Kimura().distance(new Profile("A TG"), new Profile("ACTG")), 0);
	}

	@Test
	public void distance_OneMissingInSecond_Zero() {
		assertEquals(new Kimura().distance(new Profile("ACTG"), new Profile("A TG")), 0);
	}

	@Test
	public void distance_OneTransition_Value() {
		assertEquals(new Kimura().distance(new Profile("ACTG"), new Profile("GCTG")), -Math.log(0.5) / 2.0);
	}

	@Test
	public void distance_OneTransversion_Value() {
		assertEquals(new Kimura().distance(new Profile("ACTG"), new Profile("ACGG")), -Math.log(0.75 * Math.sqrt(0.5)) / 2.0);
	}

	@Test
	public void distance_OneTransitionOneTransversions_Value() {
		assertEquals(new Kimura().distance(new Profile("ACTG"), new Profile("GCGG")), -Math.log(0.25 * Math.sqrt(0.5)) / 2.0);
	}

}
