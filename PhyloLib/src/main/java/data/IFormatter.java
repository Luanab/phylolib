package data;

import exception.InvalidFormatException;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

public interface IFormatter {

    static <T> T get(String format, HashMap<String, Supplier<T>> map) throws InvalidFormatException {
        return Optional.ofNullable(map.get(format))
                .map(Supplier::get)
                .orElseThrow(() -> new InvalidFormatException(format));
    }

}
