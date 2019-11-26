package flow.optimize;

import data.DataSet;
import data.DistanceMatrix;
import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;

import java.util.HashMap;
import java.util.List;

public abstract class Optimizer extends Component {

    protected Optimizer(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
    }

    public abstract PhylogeneticTree optimize(DataSet dataset, DistanceMatrix matrix, PhylogeneticTree tree);

    public static List<Optimizer> get(Parameters parameters) throws ParameterException {
        return parameters.map("-optimizer", "-o", new HashMap<>() {{
            put("nni", NNI::new);
            put("spr", SPR::new);
            put("tbr", TBR::new);
        }});
    }

}
