package command.algorithm.goeburst;

import command.algorithm.Algorithm;
import data.matrix.Matrix;
import data.tree.Edge;
import data.tree.Tree;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GoeBURSTTest {

	private Field field;

	@BeforeClass
	public void init() throws NoSuchFieldException {
		this.field = GoeBURST.class.getDeclaredField("lvs");
		field.setAccessible(true);
	}

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ 1, new Double[][] {
						{ },
						{ 1.0 } },
						new Edge(0, 1, 1) },
				{ 2, new Double[][] {
						{ },
						{ 2.0 },
						{ 2.0, 1.0 } },
						new Edge(0, 1, 2),
						new Edge(1, 2, 1) },
				{ 3, new Double[][] {
						{ },
						{ 3.0 },
						{ 3.0, 3.0 },
						{ 3.0, 3.0, 2.0 },
						{ 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 2.0, 3.0, 3.0, 3.0, 3.0 },
						{ 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0 } },
						new Edge(0, 1, 3),
						new Edge(1, 2, 3),
						new Edge(1, 4, 3),
						new Edge(1, 5, 3),
						new Edge(1, 6, 2),
						new Edge(1, 7, 3),
						new Edge(2, 3, 2) }
		};
	}

	@Test(dataProvider = "data")
	public void process_Valid_Success(int lvs, Double[][] values, Edge... edges) throws IllegalAccessException {
		Matrix matrix = new Matrix(true, IntStream.range(0, values.length).mapToObj(String::valueOf).toArray(String[]::new), values);
		Algorithm algorithm = new GoeBURST();
		field.set(algorithm, lvs);
		List<Edge> expected = Arrays.asList(edges);

		Tree tree = algorithm.process(matrix);

		assertEquals(tree.edges().count(), expected.size());
		assertTrue(tree.edges().allMatch(i -> expected.stream().anyMatch(j -> i.from() == j.from() && i.to() == j.to() && i.distance() == j.distance())));
	}

}
