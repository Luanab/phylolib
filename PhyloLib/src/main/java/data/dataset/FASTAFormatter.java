package data.dataset;

import java.util.stream.Stream;

public final class FASTAFormatter implements IDatasetFormatter {

	@Override
	public Dataset parse(Stream<String> data) {
		return new Dataset(data.filter(line -> !line.startsWith(">"))
				.map(values -> new Profile("" /* TODO id */, values.chars()
						.map(value -> value == ' ' ? null : value)
						.boxed()
						.toArray(Integer[]::new)))
				.toArray(Profile[]::new));
	}

}
