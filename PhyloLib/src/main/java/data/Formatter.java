package data;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

public class Formatter {

    public static <T> T get(String format, HashMap<String, Supplier<T>> map) throws Exception {
        return Optional.ofNullable(map.get(format))
                .map(Supplier::get)
                .orElseThrow(() -> new Exception("Invalid file format..."));
    }

}
