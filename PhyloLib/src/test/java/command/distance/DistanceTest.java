package command.distance;

import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class DistanceTest {

	@Test
	public void process_Empty_Empty() {
		assertEquals(new Hamming().process(new Dataset(new ArrayList<>())).size(), 0);
	}

	@Test
	public void correct_Valid_Full() {
		Dataset dataset = new Dataset(new ArrayList<>() {{
			add(new Profile("", "000000"));
			add(new Profile("", "10 001"));
		}});

		Matrix matrix = new Hamming().process(dataset);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.get(0, 0), 0);
		assertEquals(matrix.get(0, 1), 3);
		assertEquals(matrix.get(1, 0), 3);
		assertEquals(matrix.get(1, 1), 0);
	}

}
