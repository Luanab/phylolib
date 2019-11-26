package flow.optimize;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;

import java.util.List;

public class NNI extends Optimizer {

    NNI(String name, String value, List<String> parameters) throws NumberOfArgumentsException {
        super(name, value, parameters, 0);
    }

    @Override
    public PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree) {
        return tree;
    }

}
