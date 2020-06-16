package command.algorithm.nj;

import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class StudierKeplerTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ new Double[][] {
						{ },
						{ 1.0 } },
						new Edge(0, 1, 1) },
				{ new Double[][] {
						{ },
						{ 2.0 },
						{ 2.0, 1.0 } },
						new Edge(3, 0, 1.5),
						new Edge(3, 1, 0.5),
						new Edge(2, 3, 0.5) },
				{ new Double[][] {
						{ },
						{ 5.0 },
						{ 9.0, 10.0 },
						{ 9.0, 10.0, 8.0 },
						{ 8.0, 9.0, 7.0, 3.0 } },
						new Edge(5, 0, 2),
						new Edge(5, 1, 3),
						new Edge(6, 2, 4),
						new Edge(6, 5, 3),
						new Edge(7, 3, 2),
						new Edge(7, 4, 1),
						new Edge(6, 7, 2) },
				{ new Double[][] {
						{ },
						{ 3.0 },
						{ 3.0, 3.0 },
						{ 3.0, 3.0, 2.0 },
						{ 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 2.0, 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0 } },
						new Edge(8, 1, 1),
						new Edge(8, 6, 1),
						new Edge(9, 2, 1),
						new Edge(9, 3, 1),
						new Edge(10, 0, 1.5),
						new Edge(10, 4, 1.5),
						new Edge(11, 5, 1.5),
						new Edge(11, 7, 1.5),
						new Edge(12, 8, 0.5),
						new Edge(12, 9, 0.5),
						new Edge(13, 10, 0),
						new Edge(13, 11, 0),
						new Edge(12, 13, 0) }
		};
	}

	@Test(dataProvider = "data")
	public void process_Valid_Success(Double[][] values, Edge... edges) {
		Matrix matrix = new Matrix(true, null, values);
		List<Edge> expected = Arrays.asList(edges);

		Tree tree = new StudierKeppler().process(matrix);

		assertEquals(tree.edges().count(), expected.size());
		assertTrue(tree.edges().allMatch(i -> expected.stream().anyMatch(j -> i.from() == j.from() && i.to() == j.to() && i.distance() == j.distance())));
	}

}
