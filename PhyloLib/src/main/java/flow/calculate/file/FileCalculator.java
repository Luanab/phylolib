package flow.calculate.file;

import data.DataSet;
import data.DistanceMatrix;
import flow.calculate.Calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public final class FileCalculator extends Calculator {

	private final String from;

	public FileCalculator(String from) {
		this.from = from;
	}

	@Override
	public DistanceMatrix calculate(DataSet dataset) throws IOException {
		return new DistanceMatrix((double[][]) Files.lines(Paths.get(from))
				.map(line -> Arrays.stream(line.split(" "))
						.map(Double::parseDouble)
						.toArray())
				.toArray());
	}

}
