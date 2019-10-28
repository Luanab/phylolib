package flow.calculate.file;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import flow.calculate.Calculator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public final class FileCalculator extends Calculator {

    private final String from;

    public FileCalculator(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 1);
        this.from = parameters.remove(0);
    }

    @Override
    public DistanceMatrix calculate(DataSet dataset) throws IOException {
        double[][] matrix = (double[][]) Files.lines(Paths.get(from))
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Double::parseDouble)
                        .toArray())
                .toArray();
        return new DistanceMatrix(dataset.size(), (i, j) -> matrix[i][j]);
    }

}
