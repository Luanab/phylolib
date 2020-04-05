package data.tree;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class NewickTest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new Newick().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new Newick().parse(Stream.of(" ")));
	}

	@Test
	public void parse_NoTree_Null() {
		assertNull(new Newick().parse(Stream.of(";")));
	}

	@Test
	public void parse_InvalidFormat_Null() {
		assertNull(new Newick().parse(Stream.of("((A:2.3,B:1:3.1,C:0.2):2;")));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("((A:2.3,B:1):3.1,C:0.2):2;");

		Tree tree = new Newick().parse(data);

		assertNull(tree.getId());
		assertEquals(tree.getDistance(), Double.valueOf(2.0));
		assertEquals(tree.getChildren().size(), 2);

		assertNull(tree.getChildren().get(0).getId());
		assertEquals(tree.getChildren().get(0).getDistance(), Double.valueOf(3.1));
		assertEquals(tree.getChildren().get(0).getChildren().size(), 2);

		assertEquals(tree.getChildren().get(0).getChildren().get(0).getId(), "A");
		assertEquals(tree.getChildren().get(0).getChildren().get(0).getDistance(), Double.valueOf(2.3));
		assertNull(tree.getChildren().get(0).getChildren().get(0).getChildren());

		assertEquals(tree.getChildren().get(0).getChildren().get(1).getId(), "B");
		assertEquals(tree.getChildren().get(0).getChildren().get(1).getDistance(), Double.valueOf(1));
		assertNull(tree.getChildren().get(0).getChildren().get(1).getChildren());

		assertEquals(tree.getChildren().get(1).getId(), "C");
		assertEquals(tree.getChildren().get(1).getDistance(), Double.valueOf(0.2));
		assertNull(tree.getChildren().get(1).getChildren());
	}

	@Test
	public void format_Empty_Empty() {
		assertEquals(new Newick().format(new Tree()), ";");
	}

	@Test
	public void format_Valid_Success() {
		Tree tree = new Tree(null, 2.0, new ArrayList<>() {{
			add(new Tree(null, 3.1, new ArrayList<>() {{
				add(new Tree("A", 2.3, null));
				add(new Tree("B", 1.0, null));
			}}));
			add(new Tree("C", 0.2, null));
		}});

		String data = new Newick().format(tree);

		assertEquals(data, "((A:2.3,B:1.0):3.1,C:0.2):2.0;");
	}

}
