package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface IWriter<R> {

    String format(R data);

    default void write(String file, R data) throws IOException {
        Files.write(Paths.get(file), format(data).getBytes());
    }

}
