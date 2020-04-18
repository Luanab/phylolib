package data.tree;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NexusTest {

	@Test
	public void parse_Empty_Empty() {
		assertTrue(new Nexus().parse(Stream.empty()).isEmpty());
	}

	@Test
	public void parse_Blank_Empty() {
		assertTrue(new Nexus().parse(Stream.of(" ")).isEmpty());
	}

	@Test
	public void parse_NoTree_Empty() {
		assertTrue(new Nexus().parse(Stream.of("BEGIN TREES;\n\tTree result = ;\nEND;")).isEmpty());
	}

	@Test
	public void parse_InvalidFormat_Empty() {
		assertTrue(new Nexus().parse(Stream.of("BEGIN TREES;\n\tTree result = ((A:2.3,B:1:3.1,C:0.2)D;\nEND;")).isEmpty());
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("BEGIN TAXA;", "\tTaxLabels A B C", "END;", "BEGIN TREES;", "\tTree result = ((A:2.3,B:1.0)_:3.1,C:0.2)_;", "END;");

		Tree tree = new Nexus().parse(data);

		assertEquals(tree.get(4).size(), 2);
		assertEquals(tree.get(4).get(0).from(), 4);
		assertEquals(tree.get(4).get(0).to(), 2);
		assertEquals(tree.get(4).get(0).distance(), 3.1);
		assertEquals(tree.get(4).get(1).from(), 4);
		assertEquals(tree.get(4).get(1).to(), 3);
		assertEquals(tree.get(4).get(1).distance(), 0.2);
		assertEquals(tree.get(2).size(), 2);
		assertEquals(tree.get(2).get(0).from(), 2);
		assertEquals(tree.get(2).get(0).to(), 0);
		assertEquals(tree.get(2).get(0).distance(), 2.3);
		assertEquals(tree.get(2).get(1).from(), 2);
		assertEquals(tree.get(2).get(1).to(), 1);
		assertEquals(tree.get(2).get(1).distance(), 1);
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
