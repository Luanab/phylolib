package data.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class NexusTest {

	@DataProvider
	public Object[][] trees() {
		return new Object[][] {
				{ Stream.empty() },
				{ Stream.of("") },
				{ Stream.of(" ") },
				{ Stream.of("BEGIN TREES;\n\tTree result = ;\nEND;") },
				{ Stream.of("BEGIN TREES;\n\tTree result = ((A:2.3,B:1:3.1,C:0.2)_;\nEND;") }
		};
	}

	@Test(dataProvider = "trees")
	public void parse_Invalid_Null(Stream<String> data) {
		assertNull(new Nexus().parse(data));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("BEGIN TAXA;", "\tTaxLabels A B C", "END;", "BEGIN TREES;", "\tTree result = ((A:2.3,B:1.0)_:3.1,C:0.2)_;", "END;");

		Tree tree = new Nexus().parse(data);

		List<Edge> edges = tree.edges();

		assertEquals(edges.size(), 4);
		assertEquals(edges.get(0).from(), 2);
		assertEquals(edges.get(0).to(), 0);
		assertEquals(edges.get(0).distance(), 2.3);
		assertEquals(edges.get(1).from(), 2);
		assertEquals(edges.get(1).to(), 1);
		assertEquals(edges.get(1).distance(), 1);
		assertEquals(edges.get(2).from(), 4);
		assertEquals(edges.get(2).to(), 2);
		assertEquals(edges.get(2).distance(), 3.1);
		assertEquals(edges.get(3).from(), 4);
		assertEquals(edges.get(3).to(), 3);
		assertEquals(edges.get(3).distance(), 0.2);
	}

	@Test
	public void format_Empty_Empty() {
		assertEquals(new Nexus().format(new Tree()), "BEGIN TREES;\n\tTree result = ;\nEND;");
	}

	@Test
	public void format_Valid_Success() {
		Tree tree = new Tree(new String[] { "A", "B", "C" });
		tree.add(new Edge(3, 0, 2.3));
		tree.add(new Edge(3, 1, 1));
		tree.add(new Edge(4, 3, 3.1));
		tree.add(new Edge(4, 2, 0.2));

		String data = new Nexus().format(tree);

		assertEquals(data, "BEGIN TREES;\n\tTree result = ((A:2.3,B:1.0)_:3.1,C:0.2)_;\nEND;");
	}

}
