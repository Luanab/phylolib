package flow.calculate;

import data.DataSet;
import data.DistanceMatrix;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;
import flow.calculate.file.FileCalculator;
import flow.calculate.profile.explicit.GrapeTreeCalculator;
import flow.calculate.profile.implicit.HammingCalculator;
import flow.calculate.profile.implicit.JukesCantorCalculator;

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
            put("hamming", HammingCalculator::new);
            put("jukescantor", JukesCantorCalculator::new);
            put("grapetree", GrapeTreeCalculator::new);
            put("file", FileCalculator::new);
        }});
    }

}
