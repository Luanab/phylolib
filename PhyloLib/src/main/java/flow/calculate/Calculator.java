package flow.calculate;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;
import flow.calculate.file.File;
import flow.calculate.profile.explicit.GrapeTree;
import flow.calculate.profile.implicit.Hamming;
import flow.calculate.profile.implicit.JukesCantor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public abstract class Calculator extends Component {

    protected Calculator(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory);
    }

    public abstract DistanceMatrix calculate(DataSet dataset) throws IOException;

    public static Calculator get(Parameters parameters) throws ParameterException {
        return parameters.map("-calculator", "-c", "hamming", new HashMap<>() {{
            put("hamming", Hamming::new);
            put("jukescantor", JukesCantor::new);
            put("grapetree", GrapeTree::new);
            put("file", File::new);
        }});
    }

}
