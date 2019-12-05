package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public interface IReader<R> {

    R parse(Stream<String> data);

    default R read(String file) throws IOException {
        return parse(Files.lines(Paths.get(file)));
    }

}
