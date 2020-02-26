package flow.optimization;

import cli.Arguments;
import cli.Options;
import data.Context;
import data.tree.Edge;
import data.tree.Tree;
import exception.ArgumentException;
import flow.Component;

import java.io.IOException;
import java.util.HashMap;

public abstract class Optimization extends Component<Tree> {

	public static final String NAME = "optimization";

	protected Optimization(Context context, Options options) {
		super(context, options, context::readTree, context::writeTree);
	}

	public static void run(Arguments arguments, Context context) throws ArgumentException, IOException {
		Component.run(arguments, context, NAME, false, new HashMap<>() {{
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
