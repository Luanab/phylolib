package flow.read;

import data.DataSet;
import exception.NumberOfArgumentsException;

import java.util.List;
import java.util.stream.Stream;

public final class MLVAReader extends Reader {

    MLVAReader(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

    @Override
    protected DataSet parse(Stream<String> data) {
        return null;
    }

}
