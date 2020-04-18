package cli;

import exception.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ArgumentsTest {

	@Test(expectedExceptions = NoCommandException.class)
	public void parse_NoCommand_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[0]);
	}

	@Test
	public void parse_Help_Null() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		assertNull(Arguments.parse(new String[] { "help" }));
	}

	@Test(expectedExceptions = InvalidCommandException.class)
	public void parse_InvalidCommand_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[] { "distanc", "distance", ":", "correct", "jukescantor", "--out=csv:output.csv" });
	}

	@Test(expectedExceptions = RepeatedCommandException.class)
	public void parse_RepeatedCommand_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[] { "distance", "hamming", ":", "distance", "kimura", "--out=csv:output.csv" });
	}

	@Test(expectedExceptions = MissingTypeException.class)
	public void parse_MissingTypeStartOfOptions_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[] { "distance", "--out=csv:output.csv" });
	}

	@Test(expectedExceptions = MissingTypeException.class)
	public void parse_MissingTypeStartOfArgs_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[] { "algorithm", ":", "correction", "jukescantor", "--out=csv:output.csv" });
	}

	@Test(expectedExceptions = MissingTypeException.class)
	public void parse_MissingTypeEndOfArgs_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[] { "correction", "jukescantor", ":", "optimization" });
	}

	@Test(expectedExceptions = InvalidTypeException.class)
	public void parse_InvalidType_Exception() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		Arguments.parse(new String[] { "correction", "hamming" });
	}

	@Test
	public void parse_ValidCommands_NotNull() throws NoCommandException, InvalidCommandException, InvalidTypeException, RepeatedCommandException, MissingTypeException {
		String[] args = new String[] { "optimization", "nni", ":", "distance", "hamming", "--dataset=snp:dataset.txt", ":", "optimization", "spr", "-o=newick:output.txt", ":", "algorithm", "upgma" };

		Arguments arguments = Arguments.parse(args);

		assertNotNull(arguments);
		assertEquals(arguments.size(), 3);
		List<Parameters> distance = arguments.get("distance");
		assertEquals(distance.size(), 1);
		assertEquals(distance.get(0).getType(), "hamming");
		assertEquals(distance.get(0).getOptions().keys().size(), 1);
		assertTrue(distance.get(0).getOptions().keys().contains("--dataset"));
		List<Parameters> algorithm = arguments.get("algorithm");
		assertEquals(algorithm.size(), 1);
		assertEquals(algorithm.get(0).getType(), "upgma");
		assertEquals(algorithm.get(0).getOptions().keys().size(), 0);
		List<Parameters> optimization = arguments.get("optimization");
		assertEquals(optimization.size(), 2);
		assertEquals(optimization.get(0).getType(), "nni");
		assertEquals(optimization.get(0).getOptions().keys().size(), 0);
		assertEquals(optimization.get(1).getType(), "spr");
		assertEquals(optimization.get(1).getOptions().keys().size(), 1);
		assertTrue(optimization.get(1).getOptions().keys().contains("-o"));
	}

}
