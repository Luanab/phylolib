package data.dataset;

import java.util.stream.Stream;

public final class MLST implements IDatasetFormatter {

	@Override
	public Dataset parse(Stream<String> data) {
		return new Dataset(data.skip(1)
				.map(line -> line.split("\\t"))
				.map(values -> new Profile(values[0], Stream.of(values).skip(1))));
	}

}
