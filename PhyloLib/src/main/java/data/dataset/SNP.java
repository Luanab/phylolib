package data.dataset;

import java.util.stream.Stream;

public final class SNP implements IDatasetFormatter {

	@Override
	public Dataset parse(Stream<String> data) {
		return new Dataset(data.map(line -> line.split("\\t", 2))
				.map(values -> new Profile(values[0], values[1])));
	}

}
