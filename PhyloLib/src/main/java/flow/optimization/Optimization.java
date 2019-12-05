package flow.optimization;

import data.Context;
import data.tree.ITreeFormatter;
import data.tree.Tree;
import flow.Component;
import flow.Parameters;
import flow.algorithm.Algorithm;

import java.util.HashMap;
import java.util.List;

public abstract class Optimization extends Component<Tree, Tree> {

    public static final String NAME = "optimization";

    public Optimization(List<String> values, int mandatory, boolean previous, boolean next) throws Exception {
        super(values, mandatory, previous, next, Context::setTree, Context::setTree, ITreeFormatter::get, ITreeFormatter::get);
    }

    public static List<Optimization> get(Parameters parameters) throws Exception {
        return Component.getAll(parameters, NAME, new String[]{Algorithm.NAME}, new String[0], new HashMap<>() {{
            put("nni", NNI::new);
            put("spr", SPR::new);
            put("tbr", TBR::new);
        }});
    }

}
