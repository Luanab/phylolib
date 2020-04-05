package data.tree;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class NexusTest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new Nexus().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new Nexus().parse(Stream.of(" ")));
	}

	@Test
	public void parse_Valid_Success() {
		Stream<String> data = Stream.of("BEGIN TAXA;", "\tTaxLabels A B C", "END;", "BEGIN TREES;", "\tTree result = ((A:2.3,B:1.0):3.1,C:0.2):2.0;", "END;");

		Tree tree = new Nexus().parse(data);

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
		assertEquals(new Nexus().format(new Tree()), "BEGIN TAXA;\n\tTaxLabels\nEND;\nBEGIN TREES;\n\tTree result = ;\nEND;");
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

		String data = new Nexus().format(tree);

		assertEquals(data, "BEGIN TAXA;\n\tTaxLabels A B C\nEND;\nBEGIN TREES;\n\tTree result = ((A:2.3,B:1.0):3.1,C:0.2):2.0;\nEND;");
	}

}
