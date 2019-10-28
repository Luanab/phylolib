package flow.process.nj;

import exception.NumberOfArgumentsException;

import java.util.List;

public final class StudierKeplerAlgorithm extends NeighbourJoiningAlgorithm {

    public StudierKeplerAlgorithm(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

}
