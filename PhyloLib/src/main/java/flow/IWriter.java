package flow;

import cli.Options;
import exception.InvalidFileException;
import exception.InvalidFormatException;

import java.io.IOException;

public interface IWriter<T> {

	void write(Options options, T value) throws InvalidFileException, IOException, InvalidFormatException;

}
