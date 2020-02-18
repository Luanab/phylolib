package data.dataset;

import java.util.stream.Stream;

public final class MLSTFormatter extends MLVAFormatter {

	@Override
	public Dataset parse(Stream<String> data) {
		return super.parse(data.skip(1));
	}

}
