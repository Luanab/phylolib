package flow.optimization;

import cli.Parameters;
import data.Context;
import data.tree.Edge;

public final class LBR extends Optimization {

	protected LBR(Context context, Parameters parameters) {
		super(context, parameters);
	}

	@Override
	protected Edge select() {
		return null;
	}

	@Override
	protected Edge join() {
		return null;
	}

	@Override
	protected void reduce(Edge edge) {

	}

}
