package data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@FunctionalInterface
public interface IReader<T> extends IFormatter {

	T parse(Stream<String> data);

	default T read(String file) throws IOException {
		try (Stream<String> data = Files.lines(Paths.get(file))) {
			return parse(data);
		}
	}

}
