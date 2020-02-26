package flow;

import cli.Options;
import exception.InvalidFileException;
import exception.InvalidFormatException;
import exception.MissingInputException;

import java.io.IOException;

public interface IReader {

	void read(Options options) throws InvalidFileException, InvalidFormatException, MissingInputException, IOException;

}
