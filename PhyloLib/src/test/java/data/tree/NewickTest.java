package data.tree;

import org.testng.annotations.Test;

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
		assertTrue(new Newick().parse(Stream.of("((A:2.3,B:1:3.1,C:0.2)D;")).isEmpty());
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("((A:2.3,B:1)C:3.1,D:0.2)E;");

		Tree tree = new Newick().parse(data);

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
		assertEquals(new Newick().format(new Tree()), ";");
	}

	@Test
	public void format_Valid_Success() {
		Tree tree = new Tree();
		tree.add(new Edge(2, 0, 2.3));
		tree.add(new Edge(2, 1, 1));
		tree.add(new Edge(4, 2, 3.1));
		tree.add(new Edge(4, 3, 0.2));

		String data = new Newick().format(tree);

		assertEquals(data, "((0:2.3,1:1.0)2:3.1,3:0.2)4;");
	}

}
