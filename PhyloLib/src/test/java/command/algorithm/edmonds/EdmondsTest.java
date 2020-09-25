package command.algorithm.edmonds;

import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class EdmondsTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ new Double[][] {
						{ },
						{ 1.0 } },
				  new Edge(1, 0, 1) },
				{ new Double[][] {
						{ },
						{ 2.0 },
						{ 2.0, 1.0 } },
				  new Edge(1, 0, 2),
				  new Edge(2, 1, 1) },
				{ new Double[][] {
						{ },
						{ 3.0 },
						{ 3.0, 3.0 },
						{ 3.0, 3.0, 2.0 },
						{ 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 2.0, 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0 } },
				  new Edge(7, 0, 3),
				  new Edge(0, 1, 3),
				  new Edge(0, 2, 3),
				  new Edge(0, 4, 3),
				  new Edge(0, 5, 3),
				  new Edge(1, 6, 2),
				  new Edge(2, 3, 2) }
		};
	}

	@Test(dataProvider = "data")
	public void process_Valid_Success(Double[][] values, Edge... edges) {
		Matrix matrix = new Matrix(true, null, values);
		List<Edge> expected = Arrays.asList(edges);

		Tree tree = new Edmonds().process(matrix);

		assertEquals(tree.edges().count(), expected.size());
		assertTrue(tree.edges().allMatch(i -> expected.stream().anyMatch(j -> i.from() == j.from() && i.to() == j.to() && i.distance() == j.distance())));
	}

}
