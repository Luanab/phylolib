package cli;

import exception.InvalidFormatException;
import exception.MissingOptionValueException;
import exception.RepeatedOptionException;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class OptionsTest {

	@Test(expectedExceptions = InvalidFormatException.class)
	public void put_OptionWithoutDash_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		new Options().put("out=newick:output.txt");
	}

	@Test(expectedExceptions = MissingOptionValueException.class)
	public void put_MissingOptionValue_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		new Options().put("--out");
	}

	@Test(expectedExceptions = MissingOptionValueException.class)
	public void put_EmptyOptionValue_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		new Options().put("--out=");
	}

	@Test(expectedExceptions = MissingOptionValueException.class)
	public void put_BlankOptionValue_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		new Options().put("--out= ");
	}

	@Test(expectedExceptions = RepeatedOptionException.class)
	public void put_RepeatedOption_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();

		options.put("--out=newick:output.txt");
		options.put("--lvs=3");
		options.put("--out=newick:output.txt");
	}

	@Test
	public void put_ValidOptions_Success() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();

		options.put("-l=7");
		options.put("--matrix=csv:matrix.csv");

		assertEquals(options.keys().size(), 2);
		assertEquals(options.remove("lvs", 'l', Format.NATURAL).get(), "7");
		assertEquals(options.remove("matrix", 'm', Format.FILE).get(), "csv:matrix.csv");
	}

	@Test(expectedExceptions = InvalidFormatException.class)
	public void remove_InvalidNaturalFormat_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("-l=-4");

		options.remove("lvs", 'l', Format.NATURAL, "2");
	}

	@Test(expectedExceptions = InvalidFormatException.class)
	public void remove_InvalidFileFormat_ExceptionThrown() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("--matrix=csv:matrix.csv");

		options.remove("matrix", 'm', Format.NATURAL, "csv:matrix.csv");
	}

	@Test
	public void remove_NoMatch_DefaultValue() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("-t=1");
		options.put("--lts=2");

		String value = options.remove("lvs", 'l', Format.NATURAL, "3");

		assertEquals(value, "3");
		assertEquals(options.keys().size(), 2);
		assertTrue(options.keys().contains("-t"));
		assertTrue(options.keys().contains("--lts"));
	}

	@Test
	public void remove_KeyWithDefault_PreviousValue() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("--lvs=5");

		String value = options.remove("lvs", 'l', Format.NATURAL, "3");

		assertEquals(value, "5");
		assertTrue(options.keys().isEmpty());
	}

	@Test
	public void remove_AliasWithDefault_PreviousValue() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("-l=7");

		String value = options.remove("lvs", 'l', Format.NATURAL, "3");

		assertEquals(value, "7");
		assertTrue(options.keys().isEmpty());
	}

	@Test
	public void remove_NoMatch_Empty() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("--tree=nexus:tree.txt");

		Optional<String> value = options.remove("tee", 't', Format.FILE);

		assertTrue(value.isEmpty());
		assertEquals(options.keys().size(), 1);
		assertTrue(options.keys().contains("--tree"));
	}

	@Test
	public void remove_Key_PreviousValue() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("--lvs=3");

		Optional<String> value = options.remove("lvs", 'l', Format.NATURAL);

		assertTrue(value.isPresent());
		assertEquals(value.get(), "3");
		assertTrue(options.keys().isEmpty());
	}

	@Test
	public void remove_Alias_PreviousValue() throws RepeatedOptionException, InvalidFormatException, MissingOptionValueException {
		Options options = new Options();
		options.put("--matrix=csv:matrix.txt");
		options.put("--dataset=mlva:dataset.txt");
		options.put("--tree=newick:tree.txt");

		Optional<String> value = options.remove("dataset", 'd', Format.FILE);

		assertTrue(value.isPresent());
		assertEquals(value.get(), "mlva:dataset.txt");
		assertEquals(options.keys().size(), 2);
		assertTrue(options.keys().contains("--matrix"));
		assertTrue(options.keys().contains("--tree"));
	}

}
