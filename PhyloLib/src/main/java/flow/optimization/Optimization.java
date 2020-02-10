package flow.optimization;

import cli.Commands;
import cli.Parameters;
import data.Context;
import data.tree.Edge;
import data.tree.ITreeFormatter;
import data.tree.Tree;
import exception.InvalidFormatException;
import exception.InvalidTypeException;
import exception.MissingOptionException;
import flow.Component;

import java.io.IOException;
import java.util.HashMap;

public abstract class Optimization extends Component<Tree> {

    protected Optimization(Context context, Parameters parameters) {
        super(context, context::setTree, ITreeFormatter::get, parameters, false, false, true);
    }

    public static void run(Commands commands, Context context) throws InvalidTypeException, InvalidFormatException, MissingOptionException, IOException {
        Component.runAll(commands, context, "optimization", new HashMap<>() {{
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
