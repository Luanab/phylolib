package flow.write;

import data.PhylogeneticTree;
import exception.NumberOfArgumentsException;
import exception.ParameterException;
import flow.Component;
import flow.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public abstract class Writer extends Component {

    private final String to;

    Writer(String name, String value, List<String> parameters, int mandatory) throws NumberOfArgumentsException {
        super(name, value, parameters, mandatory + 1);
        this.to = parameters.remove(0);
    }

    protected abstract String format(PhylogeneticTree tree);

    public final void write(PhylogeneticTree tree) throws IOException {
        Files.write(Paths.get(to), format(tree).getBytes());
    }

    public static Writer get(Parameters parameters) throws ParameterException {
        return parameters.map("-writer", "-w", null, new HashMap<>() {{
            put("newick", NewickWriter::new);
            put("nexus", NexusWriter::new);
        }});
    }

}
