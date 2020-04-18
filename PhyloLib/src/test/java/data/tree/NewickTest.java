package data.tree;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NewickTest {

	@Test
	public void parse_Empty_Empty() {
		assertTrue(new Newick().parse(Stream.empty()).isEmpty());
	}

	@Test
	public void parse_Blank_Empty() {
		assertTrue(new Newick().parse(Stream.of(" ")).isEmpty());
	}

	@Test
	public void parse_NoTree_Empty() {
		assertTrue(new Newick().parse(Stream.of(";")).isEmpty());
	}

	@Test
	public void parse_InvalidFormat_Empty() {
		assertTrue(new Newick().parse(Stream.of("((A:2.3,B:1.0)_:3.1C:0.2)_;")).isEmpty());
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("((A:2.3,B:1.0)_:3.1,C:0.2)_;");

		Tree tree = new Newick().parse(data);

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
		assertEquals(new Newick().format(new Tree()), ";");
	}

	@Test
	public void format_Valid_Success() {
		Tree tree = new Tree(new String[]{ "A", "B", "C" });
		tree.add(new Edge(3, 0, 2.3));
		tree.add(new Edge(3, 1, 1));
		tree.add(new Edge(4, 3, 3.1));
		tree.add(new Edge(4, 2, 0.2));

		String data = new Newick().format(tree);

		assertEquals(data, "((A:2.3,B:1.0)_:3.1,C:0.2)_;");
	}

}
