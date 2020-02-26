package flow.correction;

import cli.Arguments;
import cli.Options;
import data.Context;
import data.matrix.Matrix;
import exception.ArgumentException;
import flow.Component;

import java.io.IOException;
import java.util.HashMap;

public abstract class Correction extends Component<Matrix> {

	public static final String NAME = "correction";

	protected Correction(Context context, Options options) {
		super(context, options, context::readMatrix, context::writeMatrix);
	}

	public static void run(Arguments arguments, Context context) throws ArgumentException, IOException {
		Component.run(arguments, context, NAME, true, new HashMap<>() {{
			put("jukescantor", JukesCantor::new);
		}});
	}

}
