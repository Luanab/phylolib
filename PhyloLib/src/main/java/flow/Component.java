package flow;

import exception.NumberOfArgumentsException;

import java.util.List;

public abstract class Component {

    protected Component(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        if (parameters.size() < mandatory)
            throw new NumberOfArgumentsException(name, value, mandatory, parameters.size());
    }

}
