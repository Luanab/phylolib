package data;

import exception.InvalidFileFormatException;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

public interface IFormatter {

    static <T> T get(String format, HashMap<String, Supplier<T>> map) throws InvalidFileFormatException {
        return Optional.ofNullable(map.get(format))
                .map(Supplier::get)
                .orElseThrow(() -> new InvalidFileFormatException(format));
    }

}
