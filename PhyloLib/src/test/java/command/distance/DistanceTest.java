package command.distance;

import data.dataset.Dataset;
import data.dataset.Profile;
import data.matrix.Matrix;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class DistanceTest {

	@Test
	public void process_Valid_Full() {
		Dataset dataset = new Dataset(new ArrayList<>() {{
			add(new Profile("", "000000"));
			add(new Profile("", "10 001"));
		}});

		Matrix matrix = new Hamming().process(dataset);

		assertEquals(matrix.size(), 2);
		assertEquals(matrix.distance(0, 0), 0);
		assertEquals(matrix.distance(0, 1), 3);
		assertEquals(matrix.distance(1, 0), 3);
		assertEquals(matrix.distance(1, 1), 0);
	}

}
