package flow;

import exception.InvalidFileFormatException;

import java.io.IOException;

@FunctionalInterface
public interface IMapper<T> {

    T get(String format) throws IOException, InvalidFileFormatException;

}
