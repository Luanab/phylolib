package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@FunctionalInterface
public interface IWriter<T> extends IFormatter {

    String format(T data);

    default void write(String file, T data) throws IOException {
        Files.write(Paths.get(file), format(data).getBytes());
    }

}
