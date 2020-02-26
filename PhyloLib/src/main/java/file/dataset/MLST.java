package file.dataset;

import data.dataset.Dataset;

import java.util.stream.Stream;

public final class MLST extends MLVA {

	@Override
	public Dataset parse(Stream<String> data) {
		return super.parse(data.skip(1));
	}

}
