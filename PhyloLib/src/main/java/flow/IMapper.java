package flow;

import exception.InvalidFormatException;

import java.io.IOException;

@FunctionalInterface
public interface IMapper<T> {

    T get(String format) throws IOException, InvalidFormatException;

}
