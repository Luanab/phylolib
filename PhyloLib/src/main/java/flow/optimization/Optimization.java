package flow.optimization;

import data.Context;
import data.tree.Edge;
import data.tree.ITreeFormatter;
import data.tree.Tree;
import flow.Component;
import flow.Parameters;

import java.util.HashMap;

public abstract class Optimization extends Component<Tree> {

    public Optimization(Context context, HashMap<String, String> values) throws Exception {
        super(context, context::setTree, ITreeFormatter::get, values, false, false, true);
    }

    public static void run(Parameters parameters, Context context) throws Exception {
        Component.runAll(parameters, context, "optimization", new HashMap<>() {{
            put("lbr", LBR::new);
            put("nni", NNI::new);
            put("spr", SPR::new);
            put("tbr", TBR::new);
        }});
    }

    protected abstract Edge select();

    protected abstract Edge join();

    protected abstract void reduce(Edge edge);

    @Override
    protected Tree process() {
        return null;
    }

}
