package file.dataset;

import data.dataset.Dataset;
import data.dataset.Profile;

import java.util.stream.Stream;

public final class SNP implements IDatasetFormatter {

	@Override
	public Dataset parse(Stream<String> data) {
		return new Dataset(data.map(line -> line.split("\\t"))
				.map(values -> new Profile(values[0], values[1].chars()
						.map(value -> value == ' ' ? null : value)
						.boxed()
						.toArray(Integer[]::new)))
				.toArray(Profile[]::new));
	}

}