package data.dataset;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MLTest {

	@Test
	public void parse_Empty_Null() {
		assertNull(new ML().parse(Stream.empty()));
	}

	@Test
	public void parse_Blank_Null() {
		assertNull(new ML().parse(Stream.of(" ")));
	}

	@DataProvider
	public Object[][] profiles() {
		return new Object[][] {
				{ "ST\taroe\taroi\tarou", "", "1\t1\t2\t1\t1", "2\t \t2\t1\t1" },
				{ "ST\taroe\taroi\tarou", "1", "1\t1\t2\t1\t1", "2\t \t2\t1\t1" },
				{ "ST\taroe\taroi\tarou", "1\t1", "1\t1\t2\t1\t1", "2\t \t2\t1\t1" },
				{ "ST\taroe\taroi\tarou", "1\t1\t2\t1\t1", "2\t \t2\t1\t1", "3\t1\t2\t3" },
				{ "ST\taroe\taroi\tarou", "1\t1\t2\t1\t1", "2\t \t2\t1\t1", "3\t1\t2\t3\t4\t5" },
				{ "ST\taroe\taroi\tarou", "1\t1\t2\t1\t1", "2\t \t2\t1\t1", "3\tx\t2\t3\t4" },
				{ "ST\taroe\taroi\tarou", "1\t1\t2\t1\t1", "2\t \t2\t1\t1" },
		};
	}

	@Test(dataProvider = "profiles")
	public void parse_Valid_Success(String... profiles) {
		Stream<String> data = Stream.of(profiles);

		Dataset dataset = new ML().parse(data);

		assertEquals(dataset.size(), 2);
		assertEquals(dataset.profile(0).size(), 4);
		assertEquals(dataset.profile(0).id(), "1");
		assertEquals(dataset.profile(1).size(), 4);
		assertEquals(dataset.profile(1).id(), "2");
		assertNull(dataset.profile(1).locus(0));
	}

}
