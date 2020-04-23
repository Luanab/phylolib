package cli;

import command.algorithm.bt.gcp.UPGMA;
import command.distance.Hamming;
import command.optimization.rearrangement.NNI;
import command.optimization.rearrangement.SPR;
import exception.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ArgumentsTest {

	@DataProvider
	public Object[][] data() {
		return new Object[][] {
				{ NoCommandException.class, new String[0] },
				{ InvalidCommandException.class, new String[] { "distanc", "hamming", ":", "correct", "jukescantor", "--out=csv:output.csv" } },
				{ RepeatedCommandException.class, new String[] { "distance", "hamming", ":", "distance", "kimura", "--out=csv:output.csv" } },
				{ MissingTypeException.class, new String[] { "distance", "--out=csv:output.csv" } },
				{ MissingTypeException.class, new String[] { "algorithm", ":", "correction", "jukescantor", "--out=csv:output.csv" } },
				{ MissingTypeException.class, new String[] { "correction", "jukescantor", ":", "optimization" } },
				{ InvalidTypeException.class, new String[] { "correction", "hamming" } }
		};
	}

	@Test(dataProvider = "data")
	public void get_Invalid_Exception(Class<Throwable> exception, String[] data) {
		assertThrows(exception, () -> Arguments.parse(data));
	}

	@Test
	public void parse_Help_Null() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		assertNull(Arguments.parse(new String[] { "help" }));
	}

	@Test
	public void parse_ValidCommands_NotNull() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		String[] args = new String[] { "optimization", "nni", ":", "distance", "hamming", "--dataset=snp:dataset.txt", ":", "optimization", "spr", "-o=newick:output.txt", ":", "algorithm", "upgma" };

		Arguments arguments = Arguments.parse(args);

		assertNotNull(arguments);
		assertEquals(arguments.size(), 3);
		List<Parameters> distance = arguments.get(Command.DISTANCE);
		assertEquals(distance.size(), 1);
		assertEquals(distance.get(0).type().getDeclaringClass(), Hamming.class);
		assertEquals(distance.get(0).options().keys().size(), 1);
		assertEquals(distance.get(0).options().remove(Option.DATASET), "snp:dataset.txt");
		List<Parameters> algorithm = arguments.get(Command.ALGORITHM);
		assertEquals(algorithm.size(), 1);
		assertEquals(algorithm.get(0).type().getDeclaringClass(), UPGMA.class);
		assertEquals(algorithm.get(0).options().keys().size(), 0);
		List<Parameters> optimization = arguments.get(Command.OPTIMIZATION);
		assertEquals(optimization.size(), 2);
		assertEquals(optimization.get(0).type().getDeclaringClass(), NNI.class);
		assertEquals(optimization.get(0).options().keys().size(), 0);
		assertEquals(optimization.get(1).type().getDeclaringClass(), SPR.class);
		assertEquals(optimization.get(1).options().keys().size(), 1);
		assertEquals(optimization.get(1).options().remove(Option.OUT), "newick:output.txt");
	}

}
